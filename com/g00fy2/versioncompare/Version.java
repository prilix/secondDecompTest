package com.g00fy2.versioncompare;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Version implements Comparable<Version> {
    @Nullable
    private final String originalString;
    @Nonnull
    private final List<Integer> subversionNumbers;
    @Nonnull
    private String suffix;

    public Version(@Nullable String str) {
        this(str, false);
    }

    public Version(@Nullable String str, boolean z) {
        this.subversionNumbers = new ArrayList();
        this.suffix = "";
        if (z) {
            Objects.requireNonNull(str, "Argument versionString is null");
            if (!VersionComparator.startsNumeric(str)) {
                throw new IllegalArgumentException("Argument versionString is no valid version");
            }
        }
        this.originalString = str;
        initVersion();
    }

    public int getMajor() {
        if (this.subversionNumbers.size() > 0) {
            return this.subversionNumbers.get(0).intValue();
        }
        return 0;
    }

    public int getMinor() {
        if (this.subversionNumbers.size() > 1) {
            return this.subversionNumbers.get(1).intValue();
        }
        return 0;
    }

    public int getPatch() {
        if (this.subversionNumbers.size() > 2) {
            return this.subversionNumbers.get(2).intValue();
        }
        return 0;
    }

    @Nonnull
    public List<Integer> getSubversionNumbers() {
        return this.subversionNumbers;
    }

    @Nonnull
    public String getSuffix() {
        return this.suffix;
    }

    @Nullable
    public String getOriginalString() {
        return this.originalString;
    }

    public boolean isHigherThan(String str) {
        return isHigherThan(new Version(str));
    }

    public boolean isHigherThan(Version version) {
        int compareSubversionNumbers = VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers);
        if (compareSubversionNumbers != 0) {
            return compareSubversionNumbers > 0;
        }
        if (VersionComparator.compareSuffix(this.suffix, version.suffix) > 0) {
            return true;
        }
        return false;
    }

    public boolean isLowerThan(String str) {
        return isLowerThan(new Version(str));
    }

    public boolean isLowerThan(Version version) {
        int compareSubversionNumbers = VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers);
        if (compareSubversionNumbers != 0) {
            return compareSubversionNumbers < 0;
        }
        if (VersionComparator.compareSuffix(this.suffix, version.suffix) < 0) {
            return true;
        }
        return false;
    }

    public boolean isEqual(String str) {
        return isEqual(new Version(str));
    }

    public boolean isEqual(Version version) {
        return VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers) == 0 && VersionComparator.compareSuffix(this.suffix, version.suffix) == 0;
    }

    public boolean isAtLeast(String str) {
        return isAtLeast(new Version(str));
    }

    public boolean isAtLeast(Version version) {
        return isAtLeast(version, false);
    }

    public boolean isAtLeast(String str, boolean z) {
        return isAtLeast(new Version(str), z);
    }

    public boolean isAtLeast(Version version, boolean z) {
        int compareSubversionNumbers = VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers);
        if (compareSubversionNumbers != 0 || z) {
            if (compareSubversionNumbers >= 0) {
                return true;
            }
            return false;
        } else if (VersionComparator.compareSuffix(this.suffix, version.suffix) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private void initVersion() {
        String str = this.originalString;
        if (str != null && VersionComparator.startsNumeric(str)) {
            StringBuilder sb = null;
            boolean z = false;
            for (String str2 : this.originalString.replaceAll("\\s", "").split("\\.")) {
                if (z) {
                    sb.append(".");
                    sb.append(str2);
                } else if (VersionComparator.isNumeric(str2)) {
                    this.subversionNumbers.add(Integer.valueOf(VersionComparator.safeParseInt(str2)));
                } else {
                    int i = 0;
                    while (true) {
                        if (i >= str2.length()) {
                            break;
                        } else if (!Character.isDigit(str2.charAt(i))) {
                            sb = new StringBuilder();
                            if (i > 0) {
                                this.subversionNumbers.add(Integer.valueOf(VersionComparator.safeParseInt(str2.substring(0, i))));
                                sb.append(str2.substring(i));
                            } else {
                                sb.append(str2);
                            }
                            z = true;
                        } else {
                            i++;
                        }
                    }
                }
            }
            if (sb != null) {
                this.suffix = sb.toString();
            }
        }
    }

    public int compareTo(@Nonnull Version version) {
        if (isEqual(version)) {
            return 0;
        }
        return isLowerThan(version) ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Version) || !isEqual((Version) obj)) {
            return super.equals(obj);
        }
        return true;
    }
}
