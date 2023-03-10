package p019pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* renamed from: pl.droidsonroids.gif.RenderTask */
class RenderTask extends SafeRunnable {
    RenderTask(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    public void doWork() {
        long renderFrame = this.mGifDrawable.mNativeInfoHandle.renderFrame(this.mGifDrawable.mBuffer);
        if (renderFrame >= 0) {
            this.mGifDrawable.mNextFrameRenderTime = SystemClock.uptimeMillis() + renderFrame;
            if (this.mGifDrawable.isVisible() && this.mGifDrawable.mIsRunning && !this.mGifDrawable.mIsRenderingTriggeredOnDraw) {
                this.mGifDrawable.mExecutor.remove(this);
                this.mGifDrawable.mRenderTaskSchedule = this.mGifDrawable.mExecutor.schedule(this, renderFrame, TimeUnit.MILLISECONDS);
            }
            if (!this.mGifDrawable.mListeners.isEmpty() && this.mGifDrawable.getCurrentFrameIndex() == this.mGifDrawable.mNativeInfoHandle.getNumberOfFrames() - 1) {
                this.mGifDrawable.mInvalidationHandler.sendEmptyMessageAtTime(this.mGifDrawable.getCurrentLoop(), this.mGifDrawable.mNextFrameRenderTime);
            }
        } else {
            this.mGifDrawable.mNextFrameRenderTime = Long.MIN_VALUE;
            this.mGifDrawable.mIsRunning = false;
        }
        if (this.mGifDrawable.isVisible() && !this.mGifDrawable.mInvalidationHandler.hasMessages(-1)) {
            this.mGifDrawable.mInvalidationHandler.sendEmptyMessageAtTime(-1, 0);
        }
    }
}
