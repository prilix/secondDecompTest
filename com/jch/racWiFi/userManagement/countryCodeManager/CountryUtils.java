package com.jch.racWiFi.userManagement.countryCodeManager;

import android.content.Context;
import com.jch_hitachi.aircloudglobal.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Marker;

public class CountryUtils {
    private static List<Country> countries;
    private static Map<String, List<String>> timeZoneAndCountryISOs;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v73, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v81, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v95, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v97, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v98, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v101, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v103, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v105, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v106, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v109, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v110, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v111, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v113, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v114, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v115, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v116, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v117, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v118, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v119, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v120, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v121, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v122, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v123, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v124, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v125, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v126, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v127, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v128, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v129, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v130, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v131, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v132, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v133, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v134, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v135, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v136, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v137, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v138, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v139, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v140, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v141, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v142, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v143, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v144, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v145, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v146, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v147, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v148, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v149, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v150, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v151, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v152, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v153, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v154, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v155, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v156, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v157, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v158, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v159, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v160, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v161, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v162, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v163, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v164, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v165, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v166, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v167, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v168, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v169, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v170, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v171, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v172, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v173, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v174, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v175, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v176, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v177, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v178, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v179, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v180, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v181, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v182, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v183, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v184, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v185, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v186, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v187, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v188, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v189, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v190, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v191, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v192, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v193, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v194, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v195, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v196, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v197, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v198, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v199, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v200, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v201, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v202, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v203, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v204, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v205, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v206, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v207, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v208, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v209, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v210, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v211, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v212, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v213, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v214, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v215, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v216, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v217, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v218, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v219, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v220, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v221, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v222, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v223, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v224, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v225, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v226, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v227, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v228, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v229, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v230, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v231, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v232, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v233, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v234, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v235, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v236, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v237, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v238, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v239, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v240, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v241, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v242, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v243, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v244, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFlagDrawableResId(android.content.Context r2, com.jch.racWiFi.userManagement.countryCodeManager.Country r3) {
        /*
            int r3 = r3.getIso()
            java.lang.String r2 = r2.getString(r3)
            r2.hashCode()
            int r3 = r2.hashCode()
            r0 = 0
            r1 = -1
            switch(r3) {
                case 3107: goto L_0x0d6f;
                case 3108: goto L_0x0d64;
                case 3109: goto L_0x0d59;
                case 3110: goto L_0x0d4e;
                case 3112: goto L_0x0d43;
                case 3115: goto L_0x0d38;
                case 3116: goto L_0x0d2d;
                case 3117: goto L_0x0d22;
                case 3118: goto L_0x0d14;
                case 3120: goto L_0x0d06;
                case 3121: goto L_0x0cf8;
                case 3122: goto L_0x0cea;
                case 3123: goto L_0x0cdc;
                case 3124: goto L_0x0cce;
                case 3126: goto L_0x0cc0;
                case 3127: goto L_0x0cb2;
                case 3129: goto L_0x0ca4;
                case 3135: goto L_0x0c96;
                case 3136: goto L_0x0c88;
                case 3138: goto L_0x0c7a;
                case 3139: goto L_0x0c6c;
                case 3140: goto L_0x0c5e;
                case 3141: goto L_0x0c50;
                case 3142: goto L_0x0c42;
                case 3143: goto L_0x0c34;
                case 3144: goto L_0x0c26;
                case 3146: goto L_0x0c18;
                case 3147: goto L_0x0c0a;
                case 3148: goto L_0x0bfc;
                case 3149: goto L_0x0bee;
                case 3152: goto L_0x0be0;
                case 3153: goto L_0x0bd2;
                case 3154: goto L_0x0bc4;
                case 3157: goto L_0x0bb6;
                case 3159: goto L_0x0ba8;
                case 3160: goto L_0x0b9a;
                case 3166: goto L_0x0b8c;
                case 3168: goto L_0x0b7e;
                case 3169: goto L_0x0b70;
                case 3171: goto L_0x0b62;
                case 3172: goto L_0x0b54;
                case 3173: goto L_0x0b46;
                case 3174: goto L_0x0b38;
                case 3176: goto L_0x0b2a;
                case 3177: goto L_0x0b1c;
                case 3178: goto L_0x0b0e;
                case 3179: goto L_0x0b00;
                case 3180: goto L_0x0af2;
                case 3183: goto L_0x0ae4;
                case 3186: goto L_0x0ad6;
                case 3187: goto L_0x0ac8;
                case 3189: goto L_0x0aba;
                case 3190: goto L_0x0aac;
                case 3191: goto L_0x0a9e;
                case 3201: goto L_0x0a90;
                case 3206: goto L_0x0a82;
                case 3207: goto L_0x0a74;
                case 3209: goto L_0x0a66;
                case 3211: goto L_0x0a58;
                case 3222: goto L_0x0a4a;
                case 3230: goto L_0x0a3c;
                case 3232: goto L_0x0a2e;
                case 3234: goto L_0x0a20;
                case 3245: goto L_0x0a12;
                case 3246: goto L_0x0a04;
                case 3247: goto L_0x09f6;
                case 3267: goto L_0x09e8;
                case 3268: goto L_0x09da;
                case 3269: goto L_0x09cc;
                case 3271: goto L_0x09be;
                case 3273: goto L_0x09b0;
                case 3276: goto L_0x09a2;
                case 3290: goto L_0x0994;
                case 3291: goto L_0x0986;
                case 3293: goto L_0x0978;
                case 3294: goto L_0x096a;
                case 3295: goto L_0x095c;
                case 3296: goto L_0x094e;
                case 3297: goto L_0x0940;
                case 3298: goto L_0x0932;
                case 3301: goto L_0x0924;
                case 3302: goto L_0x0916;
                case 3303: goto L_0x0908;
                case 3305: goto L_0x08fa;
                case 3306: goto L_0x08ec;
                case 3307: goto L_0x08de;
                case 3308: goto L_0x08d0;
                case 3309: goto L_0x08c2;
                case 3310: goto L_0x08b4;
                case 3312: goto L_0x08a6;
                case 3314: goto L_0x0898;
                case 3331: goto L_0x088a;
                case 3334: goto L_0x087c;
                case 3338: goto L_0x086e;
                case 3340: goto L_0x0860;
                case 3341: goto L_0x0852;
                case 3355: goto L_0x0844;
                case 3356: goto L_0x0836;
                case 3363: goto L_0x0828;
                case 3364: goto L_0x081a;
                case 3365: goto L_0x080c;
                case 3366: goto L_0x07fe;
                case 3368: goto L_0x07f0;
                case 3369: goto L_0x07e2;
                case 3370: goto L_0x07d4;
                case 3371: goto L_0x07c6;
                case 3387: goto L_0x07b8;
                case 3395: goto L_0x07aa;
                case 3397: goto L_0x079c;
                case 3398: goto L_0x078e;
                case 3418: goto L_0x0780;
                case 3420: goto L_0x0772;
                case 3421: goto L_0x0764;
                case 3422: goto L_0x0756;
                case 3426: goto L_0x0748;
                case 3427: goto L_0x073a;
                case 3429: goto L_0x072c;
                case 3431: goto L_0x071e;
                case 3436: goto L_0x0710;
                case 3438: goto L_0x0702;
                case 3439: goto L_0x06f4;
                case 3445: goto L_0x06e6;
                case 3446: goto L_0x06d8;
                case 3447: goto L_0x06ca;
                case 3453: goto L_0x06bc;
                case 3455: goto L_0x06ae;
                case 3462: goto L_0x06a0;
                case 3463: goto L_0x0692;
                case 3464: goto L_0x0684;
                case 3465: goto L_0x0676;
                case 3466: goto L_0x0668;
                case 3469: goto L_0x065a;
                case 3476: goto L_0x064c;
                case 3478: goto L_0x063e;
                case 3479: goto L_0x0630;
                case 3480: goto L_0x0622;
                case 3481: goto L_0x0614;
                case 3482: goto L_0x0606;
                case 3483: goto L_0x05f8;
                case 3486: goto L_0x05ea;
                case 3487: goto L_0x05dc;
                case 3488: goto L_0x05ce;
                case 3489: goto L_0x05c0;
                case 3490: goto L_0x05b2;
                case 3491: goto L_0x05a4;
                case 3492: goto L_0x0596;
                case 3493: goto L_0x0588;
                case 3494: goto L_0x057a;
                case 3495: goto L_0x056c;
                case 3496: goto L_0x055e;
                case 3497: goto L_0x0550;
                case 3498: goto L_0x0542;
                case 3499: goto L_0x0534;
                case 3500: goto L_0x0526;
                case 3501: goto L_0x0518;
                case 3507: goto L_0x050a;
                case 3509: goto L_0x04fc;
                case 3511: goto L_0x04ee;
                case 3512: goto L_0x04e0;
                case 3513: goto L_0x04d2;
                case 3515: goto L_0x04c4;
                case 3518: goto L_0x04b6;
                case 3521: goto L_0x04a8;
                case 3522: goto L_0x049a;
                case 3524: goto L_0x048c;
                case 3527: goto L_0x047e;
                case 3532: goto L_0x0470;
                case 3550: goto L_0x0462;
                case 3569: goto L_0x0454;
                case 3573: goto L_0x0446;
                case 3574: goto L_0x0438;
                case 3575: goto L_0x042a;
                case 3576: goto L_0x041c;
                case 3579: goto L_0x040e;
                case 3580: goto L_0x0400;
                case 3581: goto L_0x03f2;
                case 3582: goto L_0x03e4;
                case 3586: goto L_0x03d6;
                case 3587: goto L_0x03c8;
                case 3588: goto L_0x03ba;
                case 3591: goto L_0x03ac;
                case 3593: goto L_0x039e;
                case 3600: goto L_0x0390;
                case 3635: goto L_0x0382;
                case 3645: goto L_0x0374;
                case 3649: goto L_0x0366;
                case 3651: goto L_0x0358;
                case 3653: goto L_0x034a;
                case 3662: goto L_0x033c;
                case 3663: goto L_0x032e;
                case 3664: goto L_0x0320;
                case 3665: goto L_0x0312;
                case 3666: goto L_0x0304;
                case 3668: goto L_0x02f5;
                case 3669: goto L_0x02e6;
                case 3670: goto L_0x02d7;
                case 3672: goto L_0x02c8;
                case 3673: goto L_0x02b9;
                case 3674: goto L_0x02aa;
                case 3675: goto L_0x029b;
                case 3676: goto L_0x028c;
                case 3679: goto L_0x027d;
                case 3680: goto L_0x026e;
                case 3681: goto L_0x025f;
                case 3683: goto L_0x0250;
                case 3685: goto L_0x0241;
                case 3686: goto L_0x0232;
                case 3687: goto L_0x0223;
                case 3695: goto L_0x0214;
                case 3696: goto L_0x0205;
                case 3699: goto L_0x01f6;
                case 3700: goto L_0x01e7;
                case 3702: goto L_0x01d8;
                case 3703: goto L_0x01c9;
                case 3704: goto L_0x01ba;
                case 3705: goto L_0x01ab;
                case 3706: goto L_0x019c;
                case 3707: goto L_0x018d;
                case 3710: goto L_0x017e;
                case 3712: goto L_0x016f;
                case 3714: goto L_0x0160;
                case 3715: goto L_0x0151;
                case 3718: goto L_0x0142;
                case 3724: goto L_0x0133;
                case 3730: goto L_0x0124;
                case 3742: goto L_0x0115;
                case 3748: goto L_0x0106;
                case 3749: goto L_0x00f7;
                case 3755: goto L_0x00e8;
                case 3757: goto L_0x00d9;
                case 3759: goto L_0x00ca;
                case 3761: goto L_0x00bb;
                case 3763: goto L_0x00ac;
                case 3768: goto L_0x009d;
                case 3775: goto L_0x008e;
                case 3791: goto L_0x007f;
                case 3804: goto L_0x0070;
                case 3827: goto L_0x0061;
                case 3852: goto L_0x0052;
                case 3867: goto L_0x0043;
                case 3879: goto L_0x0034;
                case 3891: goto L_0x0025;
                case 3901: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0d79
        L_0x0016:
            java.lang.String r3 = "zw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0021
            goto L_0x0d79
        L_0x0021:
            r1 = 242(0xf2, float:3.39E-43)
            goto L_0x0d79
        L_0x0025:
            java.lang.String r3 = "zm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0030
            goto L_0x0d79
        L_0x0030:
            r1 = 241(0xf1, float:3.38E-43)
            goto L_0x0d79
        L_0x0034:
            java.lang.String r3 = "za"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x003f
            goto L_0x0d79
        L_0x003f:
            r1 = 240(0xf0, float:3.36E-43)
            goto L_0x0d79
        L_0x0043:
            java.lang.String r3 = "yt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x004e
            goto L_0x0d79
        L_0x004e:
            r1 = 239(0xef, float:3.35E-43)
            goto L_0x0d79
        L_0x0052:
            java.lang.String r3 = "ye"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x005d
            goto L_0x0d79
        L_0x005d:
            r1 = 238(0xee, float:3.34E-43)
            goto L_0x0d79
        L_0x0061:
            java.lang.String r3 = "xk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x006c
            goto L_0x0d79
        L_0x006c:
            r1 = 237(0xed, float:3.32E-43)
            goto L_0x0d79
        L_0x0070:
            java.lang.String r3 = "ws"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x007b
            goto L_0x0d79
        L_0x007b:
            r1 = 236(0xec, float:3.31E-43)
            goto L_0x0d79
        L_0x007f:
            java.lang.String r3 = "wf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x008a
            goto L_0x0d79
        L_0x008a:
            r1 = 235(0xeb, float:3.3E-43)
            goto L_0x0d79
        L_0x008e:
            java.lang.String r3 = "vu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0099
            goto L_0x0d79
        L_0x0099:
            r1 = 234(0xea, float:3.28E-43)
            goto L_0x0d79
        L_0x009d:
            java.lang.String r3 = "vn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00a8
            goto L_0x0d79
        L_0x00a8:
            r1 = 233(0xe9, float:3.27E-43)
            goto L_0x0d79
        L_0x00ac:
            java.lang.String r3 = "vi"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00b7
            goto L_0x0d79
        L_0x00b7:
            r1 = 232(0xe8, float:3.25E-43)
            goto L_0x0d79
        L_0x00bb:
            java.lang.String r3 = "vg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00c6
            goto L_0x0d79
        L_0x00c6:
            r1 = 231(0xe7, float:3.24E-43)
            goto L_0x0d79
        L_0x00ca:
            java.lang.String r3 = "ve"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00d5
            goto L_0x0d79
        L_0x00d5:
            r1 = 230(0xe6, float:3.22E-43)
            goto L_0x0d79
        L_0x00d9:
            java.lang.String r3 = "vc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00e4
            goto L_0x0d79
        L_0x00e4:
            r1 = 229(0xe5, float:3.21E-43)
            goto L_0x0d79
        L_0x00e8:
            java.lang.String r3 = "va"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00f3
            goto L_0x0d79
        L_0x00f3:
            r1 = 228(0xe4, float:3.2E-43)
            goto L_0x0d79
        L_0x00f7:
            java.lang.String r3 = "uz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0102
            goto L_0x0d79
        L_0x0102:
            r1 = 227(0xe3, float:3.18E-43)
            goto L_0x0d79
        L_0x0106:
            java.lang.String r3 = "uy"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0111
            goto L_0x0d79
        L_0x0111:
            r1 = 226(0xe2, float:3.17E-43)
            goto L_0x0d79
        L_0x0115:
            java.lang.String r3 = "us"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0120
            goto L_0x0d79
        L_0x0120:
            r1 = 225(0xe1, float:3.15E-43)
            goto L_0x0d79
        L_0x0124:
            java.lang.String r3 = "ug"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x012f
            goto L_0x0d79
        L_0x012f:
            r1 = 224(0xe0, float:3.14E-43)
            goto L_0x0d79
        L_0x0133:
            java.lang.String r3 = "ua"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x013e
            goto L_0x0d79
        L_0x013e:
            r1 = 223(0xdf, float:3.12E-43)
            goto L_0x0d79
        L_0x0142:
            java.lang.String r3 = "tz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x014d
            goto L_0x0d79
        L_0x014d:
            r1 = 222(0xde, float:3.11E-43)
            goto L_0x0d79
        L_0x0151:
            java.lang.String r3 = "tw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x015c
            goto L_0x0d79
        L_0x015c:
            r1 = 221(0xdd, float:3.1E-43)
            goto L_0x0d79
        L_0x0160:
            java.lang.String r3 = "tv"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x016b
            goto L_0x0d79
        L_0x016b:
            r1 = 220(0xdc, float:3.08E-43)
            goto L_0x0d79
        L_0x016f:
            java.lang.String r3 = "tt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x017a
            goto L_0x0d79
        L_0x017a:
            r1 = 219(0xdb, float:3.07E-43)
            goto L_0x0d79
        L_0x017e:
            java.lang.String r3 = "tr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0189
            goto L_0x0d79
        L_0x0189:
            r1 = 218(0xda, float:3.05E-43)
            goto L_0x0d79
        L_0x018d:
            java.lang.String r3 = "to"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0198
            goto L_0x0d79
        L_0x0198:
            r1 = 217(0xd9, float:3.04E-43)
            goto L_0x0d79
        L_0x019c:
            java.lang.String r3 = "tn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x01a7
            goto L_0x0d79
        L_0x01a7:
            r1 = 216(0xd8, float:3.03E-43)
            goto L_0x0d79
        L_0x01ab:
            java.lang.String r3 = "tm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x01b6
            goto L_0x0d79
        L_0x01b6:
            r1 = 215(0xd7, float:3.01E-43)
            goto L_0x0d79
        L_0x01ba:
            java.lang.String r3 = "tl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x01c5
            goto L_0x0d79
        L_0x01c5:
            r1 = 214(0xd6, float:3.0E-43)
            goto L_0x0d79
        L_0x01c9:
            java.lang.String r3 = "tk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x01d4
            goto L_0x0d79
        L_0x01d4:
            r1 = 213(0xd5, float:2.98E-43)
            goto L_0x0d79
        L_0x01d8:
            java.lang.String r3 = "tj"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x01e3
            goto L_0x0d79
        L_0x01e3:
            r1 = 212(0xd4, float:2.97E-43)
            goto L_0x0d79
        L_0x01e7:
            java.lang.String r3 = "th"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x01f2
            goto L_0x0d79
        L_0x01f2:
            r1 = 211(0xd3, float:2.96E-43)
            goto L_0x0d79
        L_0x01f6:
            java.lang.String r3 = "tg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0201
            goto L_0x0d79
        L_0x0201:
            r1 = 210(0xd2, float:2.94E-43)
            goto L_0x0d79
        L_0x0205:
            java.lang.String r3 = "td"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0210
            goto L_0x0d79
        L_0x0210:
            r1 = 209(0xd1, float:2.93E-43)
            goto L_0x0d79
        L_0x0214:
            java.lang.String r3 = "tc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x021f
            goto L_0x0d79
        L_0x021f:
            r1 = 208(0xd0, float:2.91E-43)
            goto L_0x0d79
        L_0x0223:
            java.lang.String r3 = "sz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x022e
            goto L_0x0d79
        L_0x022e:
            r1 = 207(0xcf, float:2.9E-43)
            goto L_0x0d79
        L_0x0232:
            java.lang.String r3 = "sy"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x023d
            goto L_0x0d79
        L_0x023d:
            r1 = 206(0xce, float:2.89E-43)
            goto L_0x0d79
        L_0x0241:
            java.lang.String r3 = "sx"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x024c
            goto L_0x0d79
        L_0x024c:
            r1 = 205(0xcd, float:2.87E-43)
            goto L_0x0d79
        L_0x0250:
            java.lang.String r3 = "sv"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x025b
            goto L_0x0d79
        L_0x025b:
            r1 = 204(0xcc, float:2.86E-43)
            goto L_0x0d79
        L_0x025f:
            java.lang.String r3 = "st"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x026a
            goto L_0x0d79
        L_0x026a:
            r1 = 203(0xcb, float:2.84E-43)
            goto L_0x0d79
        L_0x026e:
            java.lang.String r3 = "ss"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0279
            goto L_0x0d79
        L_0x0279:
            r1 = 202(0xca, float:2.83E-43)
            goto L_0x0d79
        L_0x027d:
            java.lang.String r3 = "sr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0288
            goto L_0x0d79
        L_0x0288:
            r1 = 201(0xc9, float:2.82E-43)
            goto L_0x0d79
        L_0x028c:
            java.lang.String r3 = "so"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0297
            goto L_0x0d79
        L_0x0297:
            r1 = 200(0xc8, float:2.8E-43)
            goto L_0x0d79
        L_0x029b:
            java.lang.String r3 = "sn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02a6
            goto L_0x0d79
        L_0x02a6:
            r1 = 199(0xc7, float:2.79E-43)
            goto L_0x0d79
        L_0x02aa:
            java.lang.String r3 = "sm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02b5
            goto L_0x0d79
        L_0x02b5:
            r1 = 198(0xc6, float:2.77E-43)
            goto L_0x0d79
        L_0x02b9:
            java.lang.String r3 = "sl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02c4
            goto L_0x0d79
        L_0x02c4:
            r1 = 197(0xc5, float:2.76E-43)
            goto L_0x0d79
        L_0x02c8:
            java.lang.String r3 = "sk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02d3
            goto L_0x0d79
        L_0x02d3:
            r1 = 196(0xc4, float:2.75E-43)
            goto L_0x0d79
        L_0x02d7:
            java.lang.String r3 = "si"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02e2
            goto L_0x0d79
        L_0x02e2:
            r1 = 195(0xc3, float:2.73E-43)
            goto L_0x0d79
        L_0x02e6:
            java.lang.String r3 = "sh"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x02f1
            goto L_0x0d79
        L_0x02f1:
            r1 = 194(0xc2, float:2.72E-43)
            goto L_0x0d79
        L_0x02f5:
            java.lang.String r3 = "sg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0300
            goto L_0x0d79
        L_0x0300:
            r1 = 193(0xc1, float:2.7E-43)
            goto L_0x0d79
        L_0x0304:
            java.lang.String r3 = "se"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x030e
            goto L_0x0d79
        L_0x030e:
            r1 = 192(0xc0, float:2.69E-43)
            goto L_0x0d79
        L_0x0312:
            java.lang.String r3 = "sd"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x031c
            goto L_0x0d79
        L_0x031c:
            r1 = 191(0xbf, float:2.68E-43)
            goto L_0x0d79
        L_0x0320:
            java.lang.String r3 = "sc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x032a
            goto L_0x0d79
        L_0x032a:
            r1 = 190(0xbe, float:2.66E-43)
            goto L_0x0d79
        L_0x032e:
            java.lang.String r3 = "sb"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0338
            goto L_0x0d79
        L_0x0338:
            r1 = 189(0xbd, float:2.65E-43)
            goto L_0x0d79
        L_0x033c:
            java.lang.String r3 = "sa"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0346
            goto L_0x0d79
        L_0x0346:
            r1 = 188(0xbc, float:2.63E-43)
            goto L_0x0d79
        L_0x034a:
            java.lang.String r3 = "rw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0354
            goto L_0x0d79
        L_0x0354:
            r1 = 187(0xbb, float:2.62E-43)
            goto L_0x0d79
        L_0x0358:
            java.lang.String r3 = "ru"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0362
            goto L_0x0d79
        L_0x0362:
            r1 = 186(0xba, float:2.6E-43)
            goto L_0x0d79
        L_0x0366:
            java.lang.String r3 = "rs"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0370
            goto L_0x0d79
        L_0x0370:
            r1 = 185(0xb9, float:2.59E-43)
            goto L_0x0d79
        L_0x0374:
            java.lang.String r3 = "ro"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x037e
            goto L_0x0d79
        L_0x037e:
            r1 = 184(0xb8, float:2.58E-43)
            goto L_0x0d79
        L_0x0382:
            java.lang.String r3 = "re"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x038c
            goto L_0x0d79
        L_0x038c:
            r1 = 183(0xb7, float:2.56E-43)
            goto L_0x0d79
        L_0x0390:
            java.lang.String r3 = "qa"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x039a
            goto L_0x0d79
        L_0x039a:
            r1 = 182(0xb6, float:2.55E-43)
            goto L_0x0d79
        L_0x039e:
            java.lang.String r3 = "py"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03a8
            goto L_0x0d79
        L_0x03a8:
            r1 = 181(0xb5, float:2.54E-43)
            goto L_0x0d79
        L_0x03ac:
            java.lang.String r3 = "pw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03b6
            goto L_0x0d79
        L_0x03b6:
            r1 = 180(0xb4, float:2.52E-43)
            goto L_0x0d79
        L_0x03ba:
            java.lang.String r3 = "pt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03c4
            goto L_0x0d79
        L_0x03c4:
            r1 = 179(0xb3, float:2.51E-43)
            goto L_0x0d79
        L_0x03c8:
            java.lang.String r3 = "ps"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03d2
            goto L_0x0d79
        L_0x03d2:
            r1 = 178(0xb2, float:2.5E-43)
            goto L_0x0d79
        L_0x03d6:
            java.lang.String r3 = "pr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03e0
            goto L_0x0d79
        L_0x03e0:
            r1 = 177(0xb1, float:2.48E-43)
            goto L_0x0d79
        L_0x03e4:
            java.lang.String r3 = "pn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03ee
            goto L_0x0d79
        L_0x03ee:
            r1 = 176(0xb0, float:2.47E-43)
            goto L_0x0d79
        L_0x03f2:
            java.lang.String r3 = "pm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x03fc
            goto L_0x0d79
        L_0x03fc:
            r1 = 175(0xaf, float:2.45E-43)
            goto L_0x0d79
        L_0x0400:
            java.lang.String r3 = "pl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x040a
            goto L_0x0d79
        L_0x040a:
            r1 = 174(0xae, float:2.44E-43)
            goto L_0x0d79
        L_0x040e:
            java.lang.String r3 = "pk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0418
            goto L_0x0d79
        L_0x0418:
            r1 = 173(0xad, float:2.42E-43)
            goto L_0x0d79
        L_0x041c:
            java.lang.String r3 = "ph"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0426
            goto L_0x0d79
        L_0x0426:
            r1 = 172(0xac, float:2.41E-43)
            goto L_0x0d79
        L_0x042a:
            java.lang.String r3 = "pg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0434
            goto L_0x0d79
        L_0x0434:
            r1 = 171(0xab, float:2.4E-43)
            goto L_0x0d79
        L_0x0438:
            java.lang.String r3 = "pf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0442
            goto L_0x0d79
        L_0x0442:
            r1 = 170(0xaa, float:2.38E-43)
            goto L_0x0d79
        L_0x0446:
            java.lang.String r3 = "pe"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0450
            goto L_0x0d79
        L_0x0450:
            r1 = 169(0xa9, float:2.37E-43)
            goto L_0x0d79
        L_0x0454:
            java.lang.String r3 = "pa"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x045e
            goto L_0x0d79
        L_0x045e:
            r1 = 168(0xa8, float:2.35E-43)
            goto L_0x0d79
        L_0x0462:
            java.lang.String r3 = "om"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x046c
            goto L_0x0d79
        L_0x046c:
            r1 = 167(0xa7, float:2.34E-43)
            goto L_0x0d79
        L_0x0470:
            java.lang.String r3 = "nz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x047a
            goto L_0x0d79
        L_0x047a:
            r1 = 166(0xa6, float:2.33E-43)
            goto L_0x0d79
        L_0x047e:
            java.lang.String r3 = "nu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0488
            goto L_0x0d79
        L_0x0488:
            r1 = 165(0xa5, float:2.31E-43)
            goto L_0x0d79
        L_0x048c:
            java.lang.String r3 = "nr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0496
            goto L_0x0d79
        L_0x0496:
            r1 = 164(0xa4, float:2.3E-43)
            goto L_0x0d79
        L_0x049a:
            java.lang.String r3 = "np"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04a4
            goto L_0x0d79
        L_0x04a4:
            r1 = 163(0xa3, float:2.28E-43)
            goto L_0x0d79
        L_0x04a8:
            java.lang.String r3 = "no"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04b2
            goto L_0x0d79
        L_0x04b2:
            r1 = 162(0xa2, float:2.27E-43)
            goto L_0x0d79
        L_0x04b6:
            java.lang.String r3 = "nl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04c0
            goto L_0x0d79
        L_0x04c0:
            r1 = 161(0xa1, float:2.26E-43)
            goto L_0x0d79
        L_0x04c4:
            java.lang.String r3 = "ni"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04ce
            goto L_0x0d79
        L_0x04ce:
            r1 = 160(0xa0, float:2.24E-43)
            goto L_0x0d79
        L_0x04d2:
            java.lang.String r3 = "ng"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04dc
            goto L_0x0d79
        L_0x04dc:
            r1 = 159(0x9f, float:2.23E-43)
            goto L_0x0d79
        L_0x04e0:
            java.lang.String r3 = "nf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04ea
            goto L_0x0d79
        L_0x04ea:
            r1 = 158(0x9e, float:2.21E-43)
            goto L_0x0d79
        L_0x04ee:
            java.lang.String r3 = "ne"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x04f8
            goto L_0x0d79
        L_0x04f8:
            r1 = 157(0x9d, float:2.2E-43)
            goto L_0x0d79
        L_0x04fc:
            java.lang.String r3 = "nc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0506
            goto L_0x0d79
        L_0x0506:
            r1 = 156(0x9c, float:2.19E-43)
            goto L_0x0d79
        L_0x050a:
            java.lang.String r3 = "na"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0514
            goto L_0x0d79
        L_0x0514:
            r1 = 155(0x9b, float:2.17E-43)
            goto L_0x0d79
        L_0x0518:
            java.lang.String r3 = "mz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0522
            goto L_0x0d79
        L_0x0522:
            r1 = 154(0x9a, float:2.16E-43)
            goto L_0x0d79
        L_0x0526:
            java.lang.String r3 = "my"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0530
            goto L_0x0d79
        L_0x0530:
            r1 = 153(0x99, float:2.14E-43)
            goto L_0x0d79
        L_0x0534:
            java.lang.String r3 = "mx"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x053e
            goto L_0x0d79
        L_0x053e:
            r1 = 152(0x98, float:2.13E-43)
            goto L_0x0d79
        L_0x0542:
            java.lang.String r3 = "mw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x054c
            goto L_0x0d79
        L_0x054c:
            r1 = 151(0x97, float:2.12E-43)
            goto L_0x0d79
        L_0x0550:
            java.lang.String r3 = "mv"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x055a
            goto L_0x0d79
        L_0x055a:
            r1 = 150(0x96, float:2.1E-43)
            goto L_0x0d79
        L_0x055e:
            java.lang.String r3 = "mu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0568
            goto L_0x0d79
        L_0x0568:
            r1 = 149(0x95, float:2.09E-43)
            goto L_0x0d79
        L_0x056c:
            java.lang.String r3 = "mt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0576
            goto L_0x0d79
        L_0x0576:
            r1 = 148(0x94, float:2.07E-43)
            goto L_0x0d79
        L_0x057a:
            java.lang.String r3 = "ms"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0584
            goto L_0x0d79
        L_0x0584:
            r1 = 147(0x93, float:2.06E-43)
            goto L_0x0d79
        L_0x0588:
            java.lang.String r3 = "mr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0592
            goto L_0x0d79
        L_0x0592:
            r1 = 146(0x92, float:2.05E-43)
            goto L_0x0d79
        L_0x0596:
            java.lang.String r3 = "mq"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05a0
            goto L_0x0d79
        L_0x05a0:
            r1 = 145(0x91, float:2.03E-43)
            goto L_0x0d79
        L_0x05a4:
            java.lang.String r3 = "mp"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05ae
            goto L_0x0d79
        L_0x05ae:
            r1 = 144(0x90, float:2.02E-43)
            goto L_0x0d79
        L_0x05b2:
            java.lang.String r3 = "mo"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05bc
            goto L_0x0d79
        L_0x05bc:
            r1 = 143(0x8f, float:2.0E-43)
            goto L_0x0d79
        L_0x05c0:
            java.lang.String r3 = "mn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05ca
            goto L_0x0d79
        L_0x05ca:
            r1 = 142(0x8e, float:1.99E-43)
            goto L_0x0d79
        L_0x05ce:
            java.lang.String r3 = "mm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05d8
            goto L_0x0d79
        L_0x05d8:
            r1 = 141(0x8d, float:1.98E-43)
            goto L_0x0d79
        L_0x05dc:
            java.lang.String r3 = "ml"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05e6
            goto L_0x0d79
        L_0x05e6:
            r1 = 140(0x8c, float:1.96E-43)
            goto L_0x0d79
        L_0x05ea:
            java.lang.String r3 = "mk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x05f4
            goto L_0x0d79
        L_0x05f4:
            r1 = 139(0x8b, float:1.95E-43)
            goto L_0x0d79
        L_0x05f8:
            java.lang.String r3 = "mh"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0602
            goto L_0x0d79
        L_0x0602:
            r1 = 138(0x8a, float:1.93E-43)
            goto L_0x0d79
        L_0x0606:
            java.lang.String r3 = "mg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0610
            goto L_0x0d79
        L_0x0610:
            r1 = 137(0x89, float:1.92E-43)
            goto L_0x0d79
        L_0x0614:
            java.lang.String r3 = "mf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x061e
            goto L_0x0d79
        L_0x061e:
            r1 = 136(0x88, float:1.9E-43)
            goto L_0x0d79
        L_0x0622:
            java.lang.String r3 = "me"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x062c
            goto L_0x0d79
        L_0x062c:
            r1 = 135(0x87, float:1.89E-43)
            goto L_0x0d79
        L_0x0630:
            java.lang.String r3 = "md"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x063a
            goto L_0x0d79
        L_0x063a:
            r1 = 134(0x86, float:1.88E-43)
            goto L_0x0d79
        L_0x063e:
            java.lang.String r3 = "mc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0648
            goto L_0x0d79
        L_0x0648:
            r1 = 133(0x85, float:1.86E-43)
            goto L_0x0d79
        L_0x064c:
            java.lang.String r3 = "ma"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0656
            goto L_0x0d79
        L_0x0656:
            r1 = 132(0x84, float:1.85E-43)
            goto L_0x0d79
        L_0x065a:
            java.lang.String r3 = "ly"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0664
            goto L_0x0d79
        L_0x0664:
            r1 = 131(0x83, float:1.84E-43)
            goto L_0x0d79
        L_0x0668:
            java.lang.String r3 = "lv"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0672
            goto L_0x0d79
        L_0x0672:
            r1 = 130(0x82, float:1.82E-43)
            goto L_0x0d79
        L_0x0676:
            java.lang.String r3 = "lu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0680
            goto L_0x0d79
        L_0x0680:
            r1 = 129(0x81, float:1.81E-43)
            goto L_0x0d79
        L_0x0684:
            java.lang.String r3 = "lt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x068e
            goto L_0x0d79
        L_0x068e:
            r1 = 128(0x80, float:1.794E-43)
            goto L_0x0d79
        L_0x0692:
            java.lang.String r3 = "ls"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x069c
            goto L_0x0d79
        L_0x069c:
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x0d79
        L_0x06a0:
            java.lang.String r3 = "lr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06aa
            goto L_0x0d79
        L_0x06aa:
            r1 = 126(0x7e, float:1.77E-43)
            goto L_0x0d79
        L_0x06ae:
            java.lang.String r3 = "lk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06b8
            goto L_0x0d79
        L_0x06b8:
            r1 = 125(0x7d, float:1.75E-43)
            goto L_0x0d79
        L_0x06bc:
            java.lang.String r3 = "li"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06c6
            goto L_0x0d79
        L_0x06c6:
            r1 = 124(0x7c, float:1.74E-43)
            goto L_0x0d79
        L_0x06ca:
            java.lang.String r3 = "lc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06d4
            goto L_0x0d79
        L_0x06d4:
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x0d79
        L_0x06d8:
            java.lang.String r3 = "lb"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06e2
            goto L_0x0d79
        L_0x06e2:
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x0d79
        L_0x06e6:
            java.lang.String r3 = "la"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06f0
            goto L_0x0d79
        L_0x06f0:
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x0d79
        L_0x06f4:
            java.lang.String r3 = "kz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x06fe
            goto L_0x0d79
        L_0x06fe:
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0d79
        L_0x0702:
            java.lang.String r3 = "ky"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x070c
            goto L_0x0d79
        L_0x070c:
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x0d79
        L_0x0710:
            java.lang.String r3 = "kw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x071a
            goto L_0x0d79
        L_0x071a:
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x0d79
        L_0x071e:
            java.lang.String r3 = "kr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0728
            goto L_0x0d79
        L_0x0728:
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x0d79
        L_0x072c:
            java.lang.String r3 = "kp"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0736
            goto L_0x0d79
        L_0x0736:
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x0d79
        L_0x073a:
            java.lang.String r3 = "kn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0744
            goto L_0x0d79
        L_0x0744:
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x0d79
        L_0x0748:
            java.lang.String r3 = "km"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0752
            goto L_0x0d79
        L_0x0752:
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x0d79
        L_0x0756:
            java.lang.String r3 = "ki"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0760
            goto L_0x0d79
        L_0x0760:
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x0d79
        L_0x0764:
            java.lang.String r3 = "kh"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x076e
            goto L_0x0d79
        L_0x076e:
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x0d79
        L_0x0772:
            java.lang.String r3 = "kg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x077c
            goto L_0x0d79
        L_0x077c:
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x0d79
        L_0x0780:
            java.lang.String r3 = "ke"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x078a
            goto L_0x0d79
        L_0x078a:
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x0d79
        L_0x078e:
            java.lang.String r3 = "jp"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0798
            goto L_0x0d79
        L_0x0798:
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x0d79
        L_0x079c:
            java.lang.String r3 = "jo"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07a6
            goto L_0x0d79
        L_0x07a6:
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x0d79
        L_0x07aa:
            java.lang.String r3 = "jm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07b4
            goto L_0x0d79
        L_0x07b4:
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x0d79
        L_0x07b8:
            java.lang.String r3 = "je"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07c2
            goto L_0x0d79
        L_0x07c2:
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x0d79
        L_0x07c6:
            java.lang.String r3 = "it"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07d0
            goto L_0x0d79
        L_0x07d0:
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x0d79
        L_0x07d4:
            java.lang.String r3 = "is"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07de
            goto L_0x0d79
        L_0x07de:
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x0d79
        L_0x07e2:
            java.lang.String r3 = "ir"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07ec
            goto L_0x0d79
        L_0x07ec:
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x0d79
        L_0x07f0:
            java.lang.String r3 = "iq"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x07fa
            goto L_0x0d79
        L_0x07fa:
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x0d79
        L_0x07fe:
            java.lang.String r3 = "io"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0808
            goto L_0x0d79
        L_0x0808:
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x0d79
        L_0x080c:
            java.lang.String r3 = "in"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0816
            goto L_0x0d79
        L_0x0816:
            r1 = 100
            goto L_0x0d79
        L_0x081a:
            java.lang.String r3 = "im"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0824
            goto L_0x0d79
        L_0x0824:
            r1 = 99
            goto L_0x0d79
        L_0x0828:
            java.lang.String r3 = "il"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0832
            goto L_0x0d79
        L_0x0832:
            r1 = 98
            goto L_0x0d79
        L_0x0836:
            java.lang.String r3 = "ie"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0840
            goto L_0x0d79
        L_0x0840:
            r1 = 97
            goto L_0x0d79
        L_0x0844:
            java.lang.String r3 = "id"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x084e
            goto L_0x0d79
        L_0x084e:
            r1 = 96
            goto L_0x0d79
        L_0x0852:
            java.lang.String r3 = "hu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x085c
            goto L_0x0d79
        L_0x085c:
            r1 = 95
            goto L_0x0d79
        L_0x0860:
            java.lang.String r3 = "ht"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x086a
            goto L_0x0d79
        L_0x086a:
            r1 = 94
            goto L_0x0d79
        L_0x086e:
            java.lang.String r3 = "hr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0878
            goto L_0x0d79
        L_0x0878:
            r1 = 93
            goto L_0x0d79
        L_0x087c:
            java.lang.String r3 = "hn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0886
            goto L_0x0d79
        L_0x0886:
            r1 = 92
            goto L_0x0d79
        L_0x088a:
            java.lang.String r3 = "hk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0894
            goto L_0x0d79
        L_0x0894:
            r1 = 91
            goto L_0x0d79
        L_0x0898:
            java.lang.String r3 = "gy"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08a2
            goto L_0x0d79
        L_0x08a2:
            r1 = 90
            goto L_0x0d79
        L_0x08a6:
            java.lang.String r3 = "gw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08b0
            goto L_0x0d79
        L_0x08b0:
            r1 = 89
            goto L_0x0d79
        L_0x08b4:
            java.lang.String r3 = "gu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08be
            goto L_0x0d79
        L_0x08be:
            r1 = 88
            goto L_0x0d79
        L_0x08c2:
            java.lang.String r3 = "gt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08cc
            goto L_0x0d79
        L_0x08cc:
            r1 = 87
            goto L_0x0d79
        L_0x08d0:
            java.lang.String r3 = "gs"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08da
            goto L_0x0d79
        L_0x08da:
            r1 = 86
            goto L_0x0d79
        L_0x08de:
            java.lang.String r3 = "gr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08e8
            goto L_0x0d79
        L_0x08e8:
            r1 = 85
            goto L_0x0d79
        L_0x08ec:
            java.lang.String r3 = "gq"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x08f6
            goto L_0x0d79
        L_0x08f6:
            r1 = 84
            goto L_0x0d79
        L_0x08fa:
            java.lang.String r3 = "gp"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0904
            goto L_0x0d79
        L_0x0904:
            r1 = 83
            goto L_0x0d79
        L_0x0908:
            java.lang.String r3 = "gn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0912
            goto L_0x0d79
        L_0x0912:
            r1 = 82
            goto L_0x0d79
        L_0x0916:
            java.lang.String r3 = "gm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0920
            goto L_0x0d79
        L_0x0920:
            r1 = 81
            goto L_0x0d79
        L_0x0924:
            java.lang.String r3 = "gl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x092e
            goto L_0x0d79
        L_0x092e:
            r1 = 80
            goto L_0x0d79
        L_0x0932:
            java.lang.String r3 = "gi"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x093c
            goto L_0x0d79
        L_0x093c:
            r1 = 79
            goto L_0x0d79
        L_0x0940:
            java.lang.String r3 = "gh"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x094a
            goto L_0x0d79
        L_0x094a:
            r1 = 78
            goto L_0x0d79
        L_0x094e:
            java.lang.String r3 = "gg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0958
            goto L_0x0d79
        L_0x0958:
            r1 = 77
            goto L_0x0d79
        L_0x095c:
            java.lang.String r3 = "gf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0966
            goto L_0x0d79
        L_0x0966:
            r1 = 76
            goto L_0x0d79
        L_0x096a:
            java.lang.String r3 = "ge"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0974
            goto L_0x0d79
        L_0x0974:
            r1 = 75
            goto L_0x0d79
        L_0x0978:
            java.lang.String r3 = "gd"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0982
            goto L_0x0d79
        L_0x0982:
            r1 = 74
            goto L_0x0d79
        L_0x0986:
            java.lang.String r3 = "gb"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0990
            goto L_0x0d79
        L_0x0990:
            r1 = 73
            goto L_0x0d79
        L_0x0994:
            java.lang.String r3 = "ga"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x099e
            goto L_0x0d79
        L_0x099e:
            r1 = 72
            goto L_0x0d79
        L_0x09a2:
            java.lang.String r3 = "fr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x09ac
            goto L_0x0d79
        L_0x09ac:
            r1 = 71
            goto L_0x0d79
        L_0x09b0:
            java.lang.String r3 = "fo"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x09ba
            goto L_0x0d79
        L_0x09ba:
            r1 = 70
            goto L_0x0d79
        L_0x09be:
            java.lang.String r3 = "fm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x09c8
            goto L_0x0d79
        L_0x09c8:
            r1 = 69
            goto L_0x0d79
        L_0x09cc:
            java.lang.String r3 = "fk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x09d6
            goto L_0x0d79
        L_0x09d6:
            r1 = 68
            goto L_0x0d79
        L_0x09da:
            java.lang.String r3 = "fj"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x09e4
            goto L_0x0d79
        L_0x09e4:
            r1 = 67
            goto L_0x0d79
        L_0x09e8:
            java.lang.String r3 = "fi"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x09f2
            goto L_0x0d79
        L_0x09f2:
            r1 = 66
            goto L_0x0d79
        L_0x09f6:
            java.lang.String r3 = "et"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a00
            goto L_0x0d79
        L_0x0a00:
            r1 = 65
            goto L_0x0d79
        L_0x0a04:
            java.lang.String r3 = "es"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a0e
            goto L_0x0d79
        L_0x0a0e:
            r1 = 64
            goto L_0x0d79
        L_0x0a12:
            java.lang.String r3 = "er"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a1c
            goto L_0x0d79
        L_0x0a1c:
            r1 = 63
            goto L_0x0d79
        L_0x0a20:
            java.lang.String r3 = "eg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a2a
            goto L_0x0d79
        L_0x0a2a:
            r1 = 62
            goto L_0x0d79
        L_0x0a2e:
            java.lang.String r3 = "ee"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a38
            goto L_0x0d79
        L_0x0a38:
            r1 = 61
            goto L_0x0d79
        L_0x0a3c:
            java.lang.String r3 = "ec"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a46
            goto L_0x0d79
        L_0x0a46:
            r1 = 60
            goto L_0x0d79
        L_0x0a4a:
            java.lang.String r3 = "dz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a54
            goto L_0x0d79
        L_0x0a54:
            r1 = 59
            goto L_0x0d79
        L_0x0a58:
            java.lang.String r3 = "do"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a62
            goto L_0x0d79
        L_0x0a62:
            r1 = 58
            goto L_0x0d79
        L_0x0a66:
            java.lang.String r3 = "dm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a70
            goto L_0x0d79
        L_0x0a70:
            r1 = 57
            goto L_0x0d79
        L_0x0a74:
            java.lang.String r3 = "dk"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a7e
            goto L_0x0d79
        L_0x0a7e:
            r1 = 56
            goto L_0x0d79
        L_0x0a82:
            java.lang.String r3 = "dj"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a8c
            goto L_0x0d79
        L_0x0a8c:
            r1 = 55
            goto L_0x0d79
        L_0x0a90:
            java.lang.String r3 = "de"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0a9a
            goto L_0x0d79
        L_0x0a9a:
            r1 = 54
            goto L_0x0d79
        L_0x0a9e:
            java.lang.String r3 = "cz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0aa8
            goto L_0x0d79
        L_0x0aa8:
            r1 = 53
            goto L_0x0d79
        L_0x0aac:
            java.lang.String r3 = "cy"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ab6
            goto L_0x0d79
        L_0x0ab6:
            r1 = 52
            goto L_0x0d79
        L_0x0aba:
            java.lang.String r3 = "cx"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ac4
            goto L_0x0d79
        L_0x0ac4:
            r1 = 51
            goto L_0x0d79
        L_0x0ac8:
            java.lang.String r3 = "cv"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ad2
            goto L_0x0d79
        L_0x0ad2:
            r1 = 50
            goto L_0x0d79
        L_0x0ad6:
            java.lang.String r3 = "cu"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ae0
            goto L_0x0d79
        L_0x0ae0:
            r1 = 49
            goto L_0x0d79
        L_0x0ae4:
            java.lang.String r3 = "cr"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0aee
            goto L_0x0d79
        L_0x0aee:
            r1 = 48
            goto L_0x0d79
        L_0x0af2:
            java.lang.String r3 = "co"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0afc
            goto L_0x0d79
        L_0x0afc:
            r1 = 47
            goto L_0x0d79
        L_0x0b00:
            java.lang.String r3 = "cn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b0a
            goto L_0x0d79
        L_0x0b0a:
            r1 = 46
            goto L_0x0d79
        L_0x0b0e:
            java.lang.String r3 = "cm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b18
            goto L_0x0d79
        L_0x0b18:
            r1 = 45
            goto L_0x0d79
        L_0x0b1c:
            java.lang.String r3 = "cl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b26
            goto L_0x0d79
        L_0x0b26:
            r1 = 44
            goto L_0x0d79
        L_0x0b2a:
            java.lang.String r3 = "ck"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b34
            goto L_0x0d79
        L_0x0b34:
            r1 = 43
            goto L_0x0d79
        L_0x0b38:
            java.lang.String r3 = "ci"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b42
            goto L_0x0d79
        L_0x0b42:
            r1 = 42
            goto L_0x0d79
        L_0x0b46:
            java.lang.String r3 = "ch"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b50
            goto L_0x0d79
        L_0x0b50:
            r1 = 41
            goto L_0x0d79
        L_0x0b54:
            java.lang.String r3 = "cg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b5e
            goto L_0x0d79
        L_0x0b5e:
            r1 = 40
            goto L_0x0d79
        L_0x0b62:
            java.lang.String r3 = "cf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b6c
            goto L_0x0d79
        L_0x0b6c:
            r1 = 39
            goto L_0x0d79
        L_0x0b70:
            java.lang.String r3 = "cd"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b7a
            goto L_0x0d79
        L_0x0b7a:
            r1 = 38
            goto L_0x0d79
        L_0x0b7e:
            java.lang.String r3 = "cc"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b88
            goto L_0x0d79
        L_0x0b88:
            r1 = 37
            goto L_0x0d79
        L_0x0b8c:
            java.lang.String r3 = "ca"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0b96
            goto L_0x0d79
        L_0x0b96:
            r1 = 36
            goto L_0x0d79
        L_0x0b9a:
            java.lang.String r3 = "bz"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ba4
            goto L_0x0d79
        L_0x0ba4:
            r1 = 35
            goto L_0x0d79
        L_0x0ba8:
            java.lang.String r3 = "by"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0bb2
            goto L_0x0d79
        L_0x0bb2:
            r1 = 34
            goto L_0x0d79
        L_0x0bb6:
            java.lang.String r3 = "bw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0bc0
            goto L_0x0d79
        L_0x0bc0:
            r1 = 33
            goto L_0x0d79
        L_0x0bc4:
            java.lang.String r3 = "bt"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0bce
            goto L_0x0d79
        L_0x0bce:
            r1 = 32
            goto L_0x0d79
        L_0x0bd2:
            java.lang.String r3 = "bs"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0bdc
            goto L_0x0d79
        L_0x0bdc:
            r1 = 31
            goto L_0x0d79
        L_0x0be0:
            java.lang.String r3 = "br"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0bea
            goto L_0x0d79
        L_0x0bea:
            r1 = 30
            goto L_0x0d79
        L_0x0bee:
            java.lang.String r3 = "bo"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0bf8
            goto L_0x0d79
        L_0x0bf8:
            r1 = 29
            goto L_0x0d79
        L_0x0bfc:
            java.lang.String r3 = "bn"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c06
            goto L_0x0d79
        L_0x0c06:
            r1 = 28
            goto L_0x0d79
        L_0x0c0a:
            java.lang.String r3 = "bm"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c14
            goto L_0x0d79
        L_0x0c14:
            r1 = 27
            goto L_0x0d79
        L_0x0c18:
            java.lang.String r3 = "bl"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c22
            goto L_0x0d79
        L_0x0c22:
            r1 = 26
            goto L_0x0d79
        L_0x0c26:
            java.lang.String r3 = "bj"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c30
            goto L_0x0d79
        L_0x0c30:
            r1 = 25
            goto L_0x0d79
        L_0x0c34:
            java.lang.String r3 = "bi"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c3e
            goto L_0x0d79
        L_0x0c3e:
            r1 = 24
            goto L_0x0d79
        L_0x0c42:
            java.lang.String r3 = "bh"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c4c
            goto L_0x0d79
        L_0x0c4c:
            r1 = 23
            goto L_0x0d79
        L_0x0c50:
            java.lang.String r3 = "bg"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c5a
            goto L_0x0d79
        L_0x0c5a:
            r1 = 22
            goto L_0x0d79
        L_0x0c5e:
            java.lang.String r3 = "bf"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c68
            goto L_0x0d79
        L_0x0c68:
            r1 = 21
            goto L_0x0d79
        L_0x0c6c:
            java.lang.String r3 = "be"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c76
            goto L_0x0d79
        L_0x0c76:
            r1 = 20
            goto L_0x0d79
        L_0x0c7a:
            java.lang.String r3 = "bd"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c84
            goto L_0x0d79
        L_0x0c84:
            r1 = 19
            goto L_0x0d79
        L_0x0c88:
            java.lang.String r3 = "bb"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0c92
            goto L_0x0d79
        L_0x0c92:
            r1 = 18
            goto L_0x0d79
        L_0x0c96:
            java.lang.String r3 = "ba"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ca0
            goto L_0x0d79
        L_0x0ca0:
            r1 = 17
            goto L_0x0d79
        L_0x0ca4:
            java.lang.String r3 = "az"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0cae
            goto L_0x0d79
        L_0x0cae:
            r1 = 16
            goto L_0x0d79
        L_0x0cb2:
            java.lang.String r3 = "ax"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0cbc
            goto L_0x0d79
        L_0x0cbc:
            r1 = 15
            goto L_0x0d79
        L_0x0cc0:
            java.lang.String r3 = "aw"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0cca
            goto L_0x0d79
        L_0x0cca:
            r1 = 14
            goto L_0x0d79
        L_0x0cce:
            java.lang.String r3 = "au"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0cd8
            goto L_0x0d79
        L_0x0cd8:
            r1 = 13
            goto L_0x0d79
        L_0x0cdc:
            java.lang.String r3 = "at"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0ce6
            goto L_0x0d79
        L_0x0ce6:
            r1 = 12
            goto L_0x0d79
        L_0x0cea:
            java.lang.String r3 = "as"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0cf4
            goto L_0x0d79
        L_0x0cf4:
            r1 = 11
            goto L_0x0d79
        L_0x0cf8:
            java.lang.String r3 = "ar"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d02
            goto L_0x0d79
        L_0x0d02:
            r1 = 10
            goto L_0x0d79
        L_0x0d06:
            java.lang.String r3 = "aq"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d10
            goto L_0x0d79
        L_0x0d10:
            r1 = 9
            goto L_0x0d79
        L_0x0d14:
            java.lang.String r3 = "ao"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d1e
            goto L_0x0d79
        L_0x0d1e:
            r1 = 8
            goto L_0x0d79
        L_0x0d22:
            java.lang.String r3 = "an"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d2b
            goto L_0x0d79
        L_0x0d2b:
            r1 = 7
            goto L_0x0d79
        L_0x0d2d:
            java.lang.String r3 = "am"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d36
            goto L_0x0d79
        L_0x0d36:
            r1 = 6
            goto L_0x0d79
        L_0x0d38:
            java.lang.String r3 = "al"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d41
            goto L_0x0d79
        L_0x0d41:
            r1 = 5
            goto L_0x0d79
        L_0x0d43:
            java.lang.String r3 = "ai"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d4c
            goto L_0x0d79
        L_0x0d4c:
            r1 = 4
            goto L_0x0d79
        L_0x0d4e:
            java.lang.String r3 = "ag"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d57
            goto L_0x0d79
        L_0x0d57:
            r1 = 3
            goto L_0x0d79
        L_0x0d59:
            java.lang.String r3 = "af"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d62
            goto L_0x0d79
        L_0x0d62:
            r1 = 2
            goto L_0x0d79
        L_0x0d64:
            java.lang.String r3 = "ae"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d6d
            goto L_0x0d79
        L_0x0d6d:
            r1 = 1
            goto L_0x0d79
        L_0x0d6f:
            java.lang.String r3 = "ad"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0d78
            goto L_0x0d79
        L_0x0d78:
            r1 = 0
        L_0x0d79:
            r2 = 2131231025(0x7f080131, float:1.807812E38)
            switch(r1) {
                case 0: goto L_0x113c;
                case 1: goto L_0x1138;
                case 2: goto L_0x1134;
                case 3: goto L_0x1130;
                case 4: goto L_0x112c;
                case 5: goto L_0x1128;
                case 6: goto L_0x1124;
                case 7: goto L_0x1120;
                case 8: goto L_0x111c;
                case 9: goto L_0x1118;
                case 10: goto L_0x1114;
                case 11: goto L_0x1110;
                case 12: goto L_0x110c;
                case 13: goto L_0x1108;
                case 14: goto L_0x1104;
                case 15: goto L_0x1100;
                case 16: goto L_0x10fc;
                case 17: goto L_0x10f8;
                case 18: goto L_0x10f4;
                case 19: goto L_0x10f0;
                case 20: goto L_0x10ec;
                case 21: goto L_0x10e8;
                case 22: goto L_0x10e4;
                case 23: goto L_0x10e0;
                case 24: goto L_0x10dc;
                case 25: goto L_0x10d8;
                case 26: goto L_0x10d4;
                case 27: goto L_0x10d0;
                case 28: goto L_0x10cc;
                case 29: goto L_0x10c8;
                case 30: goto L_0x10c4;
                case 31: goto L_0x10c0;
                case 32: goto L_0x10bc;
                case 33: goto L_0x10b8;
                case 34: goto L_0x10b4;
                case 35: goto L_0x10b0;
                case 36: goto L_0x10ac;
                case 37: goto L_0x10a8;
                case 38: goto L_0x10a4;
                case 39: goto L_0x10a0;
                case 40: goto L_0x109c;
                case 41: goto L_0x1098;
                case 42: goto L_0x1094;
                case 43: goto L_0x1090;
                case 44: goto L_0x108c;
                case 45: goto L_0x1088;
                case 46: goto L_0x1084;
                case 47: goto L_0x1080;
                case 48: goto L_0x107c;
                case 49: goto L_0x1078;
                case 50: goto L_0x1074;
                case 51: goto L_0x1070;
                case 52: goto L_0x106c;
                case 53: goto L_0x1068;
                case 54: goto L_0x1064;
                case 55: goto L_0x1060;
                case 56: goto L_0x105c;
                case 57: goto L_0x1058;
                case 58: goto L_0x1054;
                case 59: goto L_0x1050;
                case 60: goto L_0x104c;
                case 61: goto L_0x1048;
                case 62: goto L_0x1044;
                case 63: goto L_0x1040;
                case 64: goto L_0x103c;
                case 65: goto L_0x1038;
                case 66: goto L_0x1034;
                case 67: goto L_0x1030;
                case 68: goto L_0x102c;
                case 69: goto L_0x1028;
                case 70: goto L_0x1024;
                case 71: goto L_0x1020;
                case 72: goto L_0x101c;
                case 73: goto L_0x1018;
                case 74: goto L_0x1014;
                case 75: goto L_0x1010;
                case 76: goto L_0x100c;
                case 77: goto L_0x1008;
                case 78: goto L_0x1004;
                case 79: goto L_0x1000;
                case 80: goto L_0x0ffc;
                case 81: goto L_0x0ff8;
                case 82: goto L_0x0ff4;
                case 83: goto L_0x0ff0;
                case 84: goto L_0x0fec;
                case 85: goto L_0x0fe8;
                case 86: goto L_0x0fe4;
                case 87: goto L_0x0fe0;
                case 88: goto L_0x0fdc;
                case 89: goto L_0x0fd8;
                case 90: goto L_0x0fd4;
                case 91: goto L_0x0fd0;
                case 92: goto L_0x0fcc;
                case 93: goto L_0x0fc8;
                case 94: goto L_0x0fc4;
                case 95: goto L_0x0fc0;
                case 96: goto L_0x0fbc;
                case 97: goto L_0x0fb8;
                case 98: goto L_0x0fb4;
                case 99: goto L_0x0fb0;
                case 100: goto L_0x0fac;
                case 101: goto L_0x0fa8;
                case 102: goto L_0x0fa4;
                case 103: goto L_0x0fa0;
                case 104: goto L_0x0f9c;
                case 105: goto L_0x0f98;
                case 106: goto L_0x0f94;
                case 107: goto L_0x0f90;
                case 108: goto L_0x0f8c;
                case 109: goto L_0x0f88;
                case 110: goto L_0x0f84;
                case 111: goto L_0x0f80;
                case 112: goto L_0x0f7c;
                case 113: goto L_0x0f78;
                case 114: goto L_0x0f74;
                case 115: goto L_0x0f70;
                case 116: goto L_0x0f6c;
                case 117: goto L_0x0f68;
                case 118: goto L_0x0f64;
                case 119: goto L_0x0f60;
                case 120: goto L_0x0f5c;
                case 121: goto L_0x0f58;
                case 122: goto L_0x0f54;
                case 123: goto L_0x0f50;
                case 124: goto L_0x0f4c;
                case 125: goto L_0x0f48;
                case 126: goto L_0x0f44;
                case 127: goto L_0x0f40;
                case 128: goto L_0x0f3c;
                case 129: goto L_0x0f38;
                case 130: goto L_0x0f34;
                case 131: goto L_0x0f30;
                case 132: goto L_0x0f2c;
                case 133: goto L_0x0f28;
                case 134: goto L_0x0f24;
                case 135: goto L_0x0f20;
                case 136: goto L_0x0f1c;
                case 137: goto L_0x0f18;
                case 138: goto L_0x0f14;
                case 139: goto L_0x0f10;
                case 140: goto L_0x0f0c;
                case 141: goto L_0x0f08;
                case 142: goto L_0x0f04;
                case 143: goto L_0x0f00;
                case 144: goto L_0x0efc;
                case 145: goto L_0x0efb;
                case 146: goto L_0x0ef8;
                case 147: goto L_0x0ef4;
                case 148: goto L_0x0ef0;
                case 149: goto L_0x0eec;
                case 150: goto L_0x0ee8;
                case 151: goto L_0x0ee4;
                case 152: goto L_0x0ee0;
                case 153: goto L_0x0edc;
                case 154: goto L_0x0ed8;
                case 155: goto L_0x0ed4;
                case 156: goto L_0x0ed0;
                case 157: goto L_0x0ecc;
                case 158: goto L_0x0ec8;
                case 159: goto L_0x0ec4;
                case 160: goto L_0x0ec0;
                case 161: goto L_0x0ebc;
                case 162: goto L_0x0eb8;
                case 163: goto L_0x0eb4;
                case 164: goto L_0x0eb0;
                case 165: goto L_0x0eac;
                case 166: goto L_0x0ea8;
                case 167: goto L_0x0ea4;
                case 168: goto L_0x0ea0;
                case 169: goto L_0x0e9c;
                case 170: goto L_0x0e98;
                case 171: goto L_0x0e94;
                case 172: goto L_0x0e90;
                case 173: goto L_0x0e8c;
                case 174: goto L_0x0e88;
                case 175: goto L_0x0e84;
                case 176: goto L_0x0e80;
                case 177: goto L_0x0e7c;
                case 178: goto L_0x0e78;
                case 179: goto L_0x0e74;
                case 180: goto L_0x0e70;
                case 181: goto L_0x0e6c;
                case 182: goto L_0x0e68;
                case 183: goto L_0x0e67;
                case 184: goto L_0x0e64;
                case 185: goto L_0x0e60;
                case 186: goto L_0x0e5c;
                case 187: goto L_0x0e58;
                case 188: goto L_0x0e54;
                case 189: goto L_0x0e50;
                case 190: goto L_0x0e4c;
                case 191: goto L_0x0e48;
                case 192: goto L_0x0e44;
                case 193: goto L_0x0e40;
                case 194: goto L_0x0e3c;
                case 195: goto L_0x0e38;
                case 196: goto L_0x0e34;
                case 197: goto L_0x0e30;
                case 198: goto L_0x0e2c;
                case 199: goto L_0x0e28;
                case 200: goto L_0x0e24;
                case 201: goto L_0x0e20;
                case 202: goto L_0x0e1c;
                case 203: goto L_0x0e18;
                case 204: goto L_0x0e14;
                case 205: goto L_0x0e13;
                case 206: goto L_0x0e0f;
                case 207: goto L_0x0e0b;
                case 208: goto L_0x0e07;
                case 209: goto L_0x0e03;
                case 210: goto L_0x0dff;
                case 211: goto L_0x0dfb;
                case 212: goto L_0x0df7;
                case 213: goto L_0x0df3;
                case 214: goto L_0x0def;
                case 215: goto L_0x0deb;
                case 216: goto L_0x0de7;
                case 217: goto L_0x0de3;
                case 218: goto L_0x0ddf;
                case 219: goto L_0x0ddb;
                case 220: goto L_0x0dd7;
                case 221: goto L_0x0dd3;
                case 222: goto L_0x0dcf;
                case 223: goto L_0x0dcb;
                case 224: goto L_0x0dc7;
                case 225: goto L_0x0dc3;
                case 226: goto L_0x0dbf;
                case 227: goto L_0x0dbb;
                case 228: goto L_0x0db7;
                case 229: goto L_0x0db3;
                case 230: goto L_0x0daf;
                case 231: goto L_0x0dab;
                case 232: goto L_0x0da7;
                case 233: goto L_0x0da3;
                case 234: goto L_0x0d9f;
                case 235: goto L_0x0d9b;
                case 236: goto L_0x0d97;
                case 237: goto L_0x0d93;
                case 238: goto L_0x0d8f;
                case 239: goto L_0x0d8e;
                case 240: goto L_0x0d8b;
                case 241: goto L_0x0d87;
                case 242: goto L_0x0d83;
                default: goto L_0x0d7f;
            }
        L_0x0d7f:
            r2 = 2131231111(0x7f080187, float:1.8078294E38)
            return r2
        L_0x0d83:
            r2 = 2131231133(0x7f08019d, float:1.8078338E38)
            return r2
        L_0x0d87:
            r2 = 2131231132(0x7f08019c, float:1.8078336E38)
            return r2
        L_0x0d8b:
            r2 = 2131231091(0x7f080173, float:1.8078253E38)
        L_0x0d8e:
            return r2
        L_0x0d8f:
            r2 = 2131231131(0x7f08019b, float:1.8078334E38)
            return r2
        L_0x0d93:
            r2 = 2131231004(0x7f08011c, float:1.8078077E38)
            return r2
        L_0x0d97:
            r2 = 2131231078(0x7f080166, float:1.8078227E38)
            return r2
        L_0x0d9b:
            r2 = 2131231130(0x7f08019a, float:1.8078332E38)
            return r2
        L_0x0d9f:
            r2 = 2131231126(0x7f080196, float:1.8078324E38)
            return r2
        L_0x0da3:
            r2 = 2131231129(0x7f080199, float:1.807833E38)
            return r2
        L_0x0da7:
            r2 = 2131231124(0x7f080194, float:1.807832E38)
            return r2
        L_0x0dab:
            r2 = 2131230924(0x7f0800cc, float:1.8077915E38)
            return r2
        L_0x0daf:
            r2 = 2131231128(0x7f080198, float:1.8078328E38)
            return r2
        L_0x0db3:
            r2 = 2131231077(0x7f080165, float:1.8078225E38)
            return r2
        L_0x0db7:
            r2 = 2131231127(0x7f080197, float:1.8078326E38)
            return r2
        L_0x0dbb:
            r2 = 2131231125(0x7f080195, float:1.8078322E38)
            return r2
        L_0x0dbf:
            r2 = 2131231123(0x7f080193, float:1.8078318E38)
            return r2
        L_0x0dc3:
            r2 = 2131231122(0x7f080192, float:1.8078316E38)
            return r2
        L_0x0dc7:
            r2 = 2131231119(0x7f08018f, float:1.807831E38)
            return r2
        L_0x0dcb:
            r2 = 2131231120(0x7f080190, float:1.8078312E38)
            return r2
        L_0x0dcf:
            r2 = 2131231105(0x7f080181, float:1.8078282E38)
            return r2
        L_0x0dd3:
            r2 = 2131231103(0x7f08017f, float:1.8078278E38)
            return r2
        L_0x0dd7:
            r2 = 2131231117(0x7f08018d, float:1.8078306E38)
            return r2
        L_0x0ddb:
            r2 = 2131231112(0x7f080188, float:1.8078296E38)
            return r2
        L_0x0ddf:
            r2 = 2131231114(0x7f08018a, float:1.80783E38)
            return r2
        L_0x0de3:
            r2 = 2131231110(0x7f080186, float:1.8078292E38)
            return r2
        L_0x0de7:
            r2 = 2131231113(0x7f080189, float:1.8078298E38)
            return r2
        L_0x0deb:
            r2 = 2131231115(0x7f08018b, float:1.8078302E38)
            return r2
        L_0x0def:
            r2 = 2131231107(0x7f080183, float:1.8078286E38)
            return r2
        L_0x0df3:
            r2 = 2131231109(0x7f080185, float:1.807829E38)
            return r2
        L_0x0df7:
            r2 = 2131231104(0x7f080180, float:1.807828E38)
            return r2
        L_0x0dfb:
            r2 = 2131231106(0x7f080182, float:1.8078284E38)
            return r2
        L_0x0dff:
            r2 = 2131231108(0x7f080184, float:1.8078288E38)
            return r2
        L_0x0e03:
            r2 = 2131230935(0x7f0800d7, float:1.8077937E38)
            return r2
        L_0x0e07:
            r2 = 2131231116(0x7f08018c, float:1.8078304E38)
            return r2
        L_0x0e0b:
            r2 = 2131231099(0x7f08017b, float:1.807827E38)
            return r2
        L_0x0e0f:
            r2 = 2131231102(0x7f08017e, float:1.8078276E38)
            return r2
        L_0x0e13:
            return r0
        L_0x0e14:
            r2 = 2131230956(0x7f0800ec, float:1.807798E38)
            return r2
        L_0x0e18:
            r2 = 2131231080(0x7f080168, float:1.807823E38)
            return r2
        L_0x0e1c:
            r2 = 2131231094(0x7f080176, float:1.807826E38)
            return r2
        L_0x0e20:
            r2 = 2131231098(0x7f08017a, float:1.8078267E38)
            return r2
        L_0x0e24:
            r2 = 2131231090(0x7f080172, float:1.8078251E38)
            return r2
        L_0x0e28:
            r2 = 2131231082(0x7f08016a, float:1.8078235E38)
            return r2
        L_0x0e2c:
            r2 = 2131231079(0x7f080167, float:1.8078229E38)
            return r2
        L_0x0e30:
            r2 = 2131231085(0x7f08016d, float:1.8078241E38)
            return r2
        L_0x0e34:
            r2 = 2131231087(0x7f08016f, float:1.8078245E38)
            return r2
        L_0x0e38:
            r2 = 2131231088(0x7f080170, float:1.8078247E38)
            return r2
        L_0x0e3c:
            r2 = 2131231072(0x7f080160, float:1.8078215E38)
            return r2
        L_0x0e40:
            r2 = 2131231086(0x7f08016e, float:1.8078243E38)
            return r2
        L_0x0e44:
            r2 = 2131231100(0x7f08017c, float:1.8078271E38)
            return r2
        L_0x0e48:
            r2 = 2131231097(0x7f080179, float:1.8078265E38)
            return r2
        L_0x0e4c:
            r2 = 2131231084(0x7f08016c, float:1.807824E38)
            return r2
        L_0x0e50:
            r2 = 2131231089(0x7f080171, float:1.807825E38)
            return r2
        L_0x0e54:
            r2 = 2131231081(0x7f080169, float:1.8078233E38)
            return r2
        L_0x0e58:
            r2 = 2131231070(0x7f08015e, float:1.807821E38)
            return r2
        L_0x0e5c:
            r2 = 2131231069(0x7f08015d, float:1.8078209E38)
            return r2
        L_0x0e60:
            r2 = 2131231083(0x7f08016b, float:1.8078237E38)
            return r2
        L_0x0e64:
            r2 = 2131231068(0x7f08015c, float:1.8078207E38)
        L_0x0e67:
            return r2
        L_0x0e68:
            r2 = 2131231066(0x7f08015a, float:1.8078203E38)
            return r2
        L_0x0e6c:
            r2 = 2131231059(0x7f080153, float:1.8078188E38)
            return r2
        L_0x0e70:
            r2 = 2131231055(0x7f08014f, float:1.807818E38)
            return r2
        L_0x0e74:
            r2 = 2131231064(0x7f080158, float:1.8078198E38)
            return r2
        L_0x0e78:
            r2 = 2131231056(0x7f080150, float:1.8078182E38)
            return r2
        L_0x0e7c:
            r2 = 2131231065(0x7f080159, float:1.80782E38)
            return r2
        L_0x0e80:
            r2 = 2131231062(0x7f080156, float:1.8078194E38)
            return r2
        L_0x0e84:
            r2 = 2131231076(0x7f080164, float:1.8078223E38)
            return r2
        L_0x0e88:
            r2 = 2131231063(0x7f080157, float:1.8078196E38)
            return r2
        L_0x0e8c:
            r2 = 2131231054(0x7f08014e, float:1.8078178E38)
            return r2
        L_0x0e90:
            r2 = 2131231061(0x7f080155, float:1.8078192E38)
            return r2
        L_0x0e94:
            r2 = 2131231058(0x7f080152, float:1.8078186E38)
            return r2
        L_0x0e98:
            r2 = 2131230966(0x7f0800f6, float:1.8078E38)
            return r2
        L_0x0e9c:
            r2 = 2131231060(0x7f080154, float:1.807819E38)
            return r2
        L_0x0ea0:
            r2 = 2131231057(0x7f080151, float:1.8078184E38)
            return r2
        L_0x0ea4:
            r2 = 2131231053(0x7f08014d, float:1.8078176E38)
            return r2
        L_0x0ea8:
            r2 = 2131231043(0x7f080143, float:1.8078156E38)
            return r2
        L_0x0eac:
            r2 = 2131231047(0x7f080147, float:1.8078164E38)
            return r2
        L_0x0eb0:
            r2 = 2131231038(0x7f08013e, float:1.8078146E38)
            return r2
        L_0x0eb4:
            r2 = 2131231039(0x7f08013f, float:1.8078148E38)
            return r2
        L_0x0eb8:
            r2 = 2131231051(0x7f08014b, float:1.8078172E38)
            return r2
        L_0x0ebc:
            r2 = 2131231040(0x7f080140, float:1.807815E38)
            return r2
        L_0x0ec0:
            r2 = 2131231044(0x7f080144, float:1.8078158E38)
            return r2
        L_0x0ec4:
            r2 = 2131231046(0x7f080146, float:1.8078162E38)
            return r2
        L_0x0ec8:
            r2 = 2131231048(0x7f080148, float:1.8078166E38)
            return r2
        L_0x0ecc:
            r2 = 2131231045(0x7f080145, float:1.807816E38)
            return r2
        L_0x0ed0:
            r2 = 2131231042(0x7f080142, float:1.8078154E38)
            return r2
        L_0x0ed4:
            r2 = 2131231037(0x7f08013d, float:1.8078144E38)
            return r2
        L_0x0ed8:
            r2 = 2131231035(0x7f08013b, float:1.807814E38)
            return r2
        L_0x0edc:
            r2 = 2131231020(0x7f08012c, float:1.807811E38)
            return r2
        L_0x0ee0:
            r2 = 2131231028(0x7f080134, float:1.8078125E38)
            return r2
        L_0x0ee4:
            r2 = 2131231019(0x7f08012b, float:1.8078107E38)
            return r2
        L_0x0ee8:
            r2 = 2131231021(0x7f08012d, float:1.8078111E38)
            return r2
        L_0x0eec:
            r2 = 2131231027(0x7f080133, float:1.8078123E38)
            return r2
        L_0x0ef0:
            r2 = 2131231023(0x7f08012f, float:1.8078115E38)
            return r2
        L_0x0ef4:
            r2 = 2131231033(0x7f080139, float:1.8078136E38)
            return r2
        L_0x0ef8:
            r2 = 2131231026(0x7f080132, float:1.8078121E38)
        L_0x0efb:
            return r2
        L_0x0efc:
            r2 = 2131231050(0x7f08014a, float:1.807817E38)
            return r2
        L_0x0f00:
            r2 = 2131231016(0x7f080128, float:1.8078101E38)
            return r2
        L_0x0f04:
            r2 = 2131231032(0x7f080138, float:1.8078134E38)
            return r2
        L_0x0f08:
            r2 = 2131231036(0x7f08013c, float:1.8078142E38)
            return r2
        L_0x0f0c:
            r2 = 2131231022(0x7f08012e, float:1.8078113E38)
            return r2
        L_0x0f10:
            r2 = 2131231017(0x7f080129, float:1.8078103E38)
            return r2
        L_0x0f14:
            r2 = 2131231024(0x7f080130, float:1.8078117E38)
            return r2
        L_0x0f18:
            r2 = 2131231018(0x7f08012a, float:1.8078105E38)
            return r2
        L_0x0f1c:
            r2 = 2131231075(0x7f080163, float:1.807822E38)
            return r2
        L_0x0f20:
            r2 = 2131231052(0x7f08014c, float:1.8078174E38)
            return r2
        L_0x0f24:
            r2 = 2131231030(0x7f080136, float:1.807813E38)
            return r2
        L_0x0f28:
            r2 = 2131231031(0x7f080137, float:1.8078132E38)
            return r2
        L_0x0f2c:
            r2 = 2131231034(0x7f08013a, float:1.8078138E38)
            return r2
        L_0x0f30:
            r2 = 2131231012(0x7f080124, float:1.8078093E38)
            return r2
        L_0x0f34:
            r2 = 2131231008(0x7f080120, float:1.8078085E38)
            return r2
        L_0x0f38:
            r2 = 2131231015(0x7f080127, float:1.80781E38)
            return r2
        L_0x0f3c:
            r2 = 2131231014(0x7f080126, float:1.8078097E38)
            return r2
        L_0x0f40:
            r2 = 2131231010(0x7f080122, float:1.8078089E38)
            return r2
        L_0x0f44:
            r2 = 2131231011(0x7f080123, float:1.807809E38)
            return r2
        L_0x0f48:
            r2 = 2131231096(0x7f080178, float:1.8078263E38)
            return r2
        L_0x0f4c:
            r2 = 2131231013(0x7f080125, float:1.8078095E38)
            return r2
        L_0x0f50:
            r2 = 2131231074(0x7f080162, float:1.8078219E38)
            return r2
        L_0x0f54:
            r2 = 2131231009(0x7f080121, float:1.8078087E38)
            return r2
        L_0x0f58:
            r2 = 2131231007(0x7f08011f, float:1.8078083E38)
            return r2
        L_0x0f5c:
            r2 = 2131231001(0x7f080119, float:1.807807E38)
            return r2
        L_0x0f60:
            r2 = 2131230933(0x7f0800d5, float:1.8077933E38)
            return r2
        L_0x0f64:
            r2 = 2131231005(0x7f08011d, float:1.8078079E38)
            return r2
        L_0x0f68:
            r2 = 2131231093(0x7f080175, float:1.8078257E38)
            return r2
        L_0x0f6c:
            r2 = 2131231049(0x7f080149, float:1.8078168E38)
            return r2
        L_0x0f70:
            r2 = 2131231073(0x7f080161, float:1.8078217E38)
            return r2
        L_0x0f74:
            r2 = 2131230941(0x7f0800dd, float:1.8077949E38)
            return r2
        L_0x0f78:
            r2 = 2131231003(0x7f08011b, float:1.8078075E38)
            return r2
        L_0x0f7c:
            r2 = 2131230929(0x7f0800d1, float:1.8077925E38)
            return r2
        L_0x0f80:
            r2 = 2131231006(0x7f08011e, float:1.807808E38)
            return r2
        L_0x0f84:
            r2 = 2131231002(0x7f08011a, float:1.8078073E38)
            return r2
        L_0x0f88:
            r2 = 2131230998(0x7f080116, float:1.8078065E38)
            return r2
        L_0x0f8c:
            r2 = 2131231000(0x7f080118, float:1.8078069E38)
            return r2
        L_0x0f90:
            r2 = 2131230997(0x7f080115, float:1.8078063E38)
            return r2
        L_0x0f94:
            r2 = 2131230999(0x7f080117, float:1.8078067E38)
            return r2
        L_0x0f98:
            r2 = 2131230996(0x7f080114, float:1.807806E38)
            return r2
        L_0x0f9c:
            r2 = 2131230988(0x7f08010c, float:1.8078044E38)
            return r2
        L_0x0fa0:
            r2 = 2131230991(0x7f08010f, float:1.807805E38)
            return r2
        L_0x0fa4:
            r2 = 2131230992(0x7f080110, float:1.8078052E38)
            return r2
        L_0x0fa8:
            r2 = 2131230923(0x7f0800cb, float:1.8077912E38)
            return r2
        L_0x0fac:
            r2 = 2131230989(0x7f08010d, float:1.8078046E38)
            return r2
        L_0x0fb0:
            r2 = 2131230994(0x7f080112, float:1.8078056E38)
            return r2
        L_0x0fb4:
            r2 = 2131230995(0x7f080113, float:1.8078059E38)
            return r2
        L_0x0fb8:
            r2 = 2131230993(0x7f080111, float:1.8078054E38)
            return r2
        L_0x0fbc:
            r2 = 2131230990(0x7f08010e, float:1.8078048E38)
            return r2
        L_0x0fc0:
            r2 = 2131230987(0x7f08010b, float:1.8078042E38)
            return r2
        L_0x0fc4:
            r2 = 2131230984(0x7f080108, float:1.8078036E38)
            return r2
        L_0x0fc8:
            r2 = 2131230945(0x7f0800e1, float:1.8077957E38)
            return r2
        L_0x0fcc:
            r2 = 2131230985(0x7f080109, float:1.8078038E38)
            return r2
        L_0x0fd0:
            r2 = 2131230986(0x7f08010a, float:1.807804E38)
            return r2
        L_0x0fd4:
            r2 = 2131230982(0x7f080106, float:1.8078032E38)
            return r2
        L_0x0fd8:
            r2 = 2131230981(0x7f080105, float:1.807803E38)
            return r2
        L_0x0fdc:
            r2 = 2131230977(0x7f080101, float:1.8078022E38)
            return r2
        L_0x0fe0:
            r2 = 2131230978(0x7f080102, float:1.8078024E38)
            return r2
        L_0x0fe4:
            r2 = 2131231092(0x7f080174, float:1.8078255E38)
            return r2
        L_0x0fe8:
            r2 = 2131230973(0x7f0800fd, float:1.8078014E38)
            return r2
        L_0x0fec:
            r2 = 2131230957(0x7f0800ed, float:1.8077981E38)
            return r2
        L_0x0ff0:
            r2 = 2131230976(0x7f080100, float:1.807802E38)
            return r2
        L_0x0ff4:
            r2 = 2131230980(0x7f080104, float:1.8078028E38)
            return r2
        L_0x0ff8:
            r2 = 2131230968(0x7f0800f8, float:1.8078004E38)
            return r2
        L_0x0ffc:
            r2 = 2131230974(0x7f0800fe, float:1.8078016E38)
            return r2
        L_0x1000:
            r2 = 2131230972(0x7f0800fc, float:1.8078012E38)
            return r2
        L_0x1004:
            r2 = 2131230971(0x7f0800fb, float:1.807801E38)
            return r2
        L_0x1008:
            r2 = 2131230979(0x7f080103, float:1.8078026E38)
            return r2
        L_0x100c:
            r2 = 2131230983(0x7f080107, float:1.8078034E38)
            return r2
        L_0x1010:
            r2 = 2131230969(0x7f0800f9, float:1.8078006E38)
            return r2
        L_0x1014:
            r2 = 2131230975(0x7f0800ff, float:1.8078018E38)
            return r2
        L_0x1018:
            r2 = 2131231121(0x7f080191, float:1.8078314E38)
            return r2
        L_0x101c:
            r2 = 2131230967(0x7f0800f7, float:1.8078002E38)
            return r2
        L_0x1020:
            r2 = 2131230965(0x7f0800f5, float:1.8077998E38)
            return r2
        L_0x1024:
            r2 = 2131230962(0x7f0800f2, float:1.8077992E38)
            return r2
        L_0x1028:
            r2 = 2131231029(0x7f080135, float:1.8078127E38)
            return r2
        L_0x102c:
            r2 = 2131230961(0x7f0800f1, float:1.807799E38)
            return r2
        L_0x1030:
            r2 = 2131230963(0x7f0800f3, float:1.8077994E38)
            return r2
        L_0x1034:
            r2 = 2131230964(0x7f0800f4, float:1.8077996E38)
            return r2
        L_0x1038:
            r2 = 2131230960(0x7f0800f0, float:1.8077988E38)
            return r2
        L_0x103c:
            r2 = 2131231095(0x7f080177, float:1.8078261E38)
            return r2
        L_0x1040:
            r2 = 2131230958(0x7f0800ee, float:1.8077983E38)
            return r2
        L_0x1044:
            r2 = 2131230955(0x7f0800eb, float:1.8077977E38)
            return r2
        L_0x1048:
            r2 = 2131230959(0x7f0800ef, float:1.8077985E38)
            return r2
        L_0x104c:
            r2 = 2131230954(0x7f0800ea, float:1.8077975E38)
            return r2
        L_0x1050:
            r2 = 2131230896(0x7f0800b0, float:1.8077858E38)
            return r2
        L_0x1054:
            r2 = 2131230953(0x7f0800e9, float:1.8077973E38)
            return r2
        L_0x1058:
            r2 = 2131230952(0x7f0800e8, float:1.8077971E38)
            return r2
        L_0x105c:
            r2 = 2131230950(0x7f0800e6, float:1.8077967E38)
            return r2
        L_0x1060:
            r2 = 2131230951(0x7f0800e7, float:1.807797E38)
            return r2
        L_0x1064:
            r2 = 2131230970(0x7f0800fa, float:1.8078008E38)
            return r2
        L_0x1068:
            r2 = 2131230948(0x7f0800e4, float:1.8077963E38)
            return r2
        L_0x106c:
            r2 = 2131230947(0x7f0800e3, float:1.8077961E38)
            return r2
        L_0x1070:
            r2 = 2131230938(0x7f0800da, float:1.8077943E38)
            return r2
        L_0x1074:
            r2 = 2131230932(0x7f0800d4, float:1.807793E38)
            return r2
        L_0x1078:
            r2 = 2131230946(0x7f0800e2, float:1.807796E38)
            return r2
        L_0x107c:
            r2 = 2131230943(0x7f0800df, float:1.8077953E38)
            return r2
        L_0x1080:
            r2 = 2131230940(0x7f0800dc, float:1.8077947E38)
            return r2
        L_0x1084:
            r2 = 2131230937(0x7f0800d9, float:1.807794E38)
            return r2
        L_0x1088:
            r2 = 2131230930(0x7f0800d2, float:1.8077927E38)
            return r2
        L_0x108c:
            r2 = 2131230936(0x7f0800d8, float:1.8077939E38)
            return r2
        L_0x1090:
            r2 = 2131230942(0x7f0800de, float:1.807795E38)
            return r2
        L_0x1094:
            r2 = 2131230944(0x7f0800e0, float:1.8077955E38)
            return r2
        L_0x1098:
            r2 = 2131231101(0x7f08017d, float:1.8078274E38)
            return r2
        L_0x109c:
            r2 = 2131231067(0x7f08015b, float:1.8078205E38)
            return r2
        L_0x10a0:
            r2 = 2131230934(0x7f0800d6, float:1.8077935E38)
            return r2
        L_0x10a4:
            r2 = 2131230949(0x7f0800e5, float:1.8077965E38)
            return r2
        L_0x10a8:
            r2 = 2131230939(0x7f0800db, float:1.8077945E38)
            return r2
        L_0x10ac:
            r2 = 2131230931(0x7f0800d3, float:1.8077929E38)
            return r2
        L_0x10b0:
            r2 = 2131230915(0x7f0800c3, float:1.8077896E38)
            return r2
        L_0x10b4:
            r2 = 2131230913(0x7f0800c1, float:1.8077892E38)
            return r2
        L_0x10b8:
            r2 = 2131230921(0x7f0800c9, float:1.8077908E38)
            return r2
        L_0x10bc:
            r2 = 2131230918(0x7f0800c6, float:1.8077902E38)
            return r2
        L_0x10c0:
            r2 = 2131230909(0x7f0800bd, float:1.8077884E38)
            return r2
        L_0x10c4:
            r2 = 2131230922(0x7f0800ca, float:1.807791E38)
            return r2
        L_0x10c8:
            r2 = 2131230919(0x7f0800c7, float:1.8077904E38)
            return r2
        L_0x10cc:
            r2 = 2131230925(0x7f0800cd, float:1.8077917E38)
            return r2
        L_0x10d0:
            r2 = 2131230917(0x7f0800c5, float:1.80779E38)
            return r2
        L_0x10d4:
            r2 = 2131231071(0x7f08015f, float:1.8078213E38)
            return r2
        L_0x10d8:
            r2 = 2131230916(0x7f0800c4, float:1.8077898E38)
            return r2
        L_0x10dc:
            r2 = 2131230928(0x7f0800d0, float:1.8077923E38)
            return r2
        L_0x10e0:
            r2 = 2131230910(0x7f0800be, float:1.8077886E38)
            return r2
        L_0x10e4:
            r2 = 2131230926(0x7f0800ce, float:1.8077919E38)
            return r2
        L_0x10e8:
            r2 = 2131230927(0x7f0800cf, float:1.807792E38)
            return r2
        L_0x10ec:
            r2 = 2131230914(0x7f0800c2, float:1.8077894E38)
            return r2
        L_0x10f0:
            r2 = 2131230911(0x7f0800bf, float:1.8077888E38)
            return r2
        L_0x10f4:
            r2 = 2131230912(0x7f0800c0, float:1.807789E38)
            return r2
        L_0x10f8:
            r2 = 2131230920(0x7f0800c8, float:1.8077906E38)
            return r2
        L_0x10fc:
            r2 = 2131230908(0x7f0800bc, float:1.8077882E38)
            return r2
        L_0x1100:
            r2 = 2131230894(0x7f0800ae, float:1.8077854E38)
            return r2
        L_0x1104:
            r2 = 2131230905(0x7f0800b9, float:1.8077876E38)
            return r2
        L_0x1108:
            r2 = 2131230906(0x7f0800ba, float:1.8077878E38)
            return r2
        L_0x110c:
            r2 = 2131230907(0x7f0800bb, float:1.807788E38)
            return r2
        L_0x1110:
            r2 = 2131230897(0x7f0800b1, float:1.807786E38)
            return r2
        L_0x1114:
            r2 = 2131230903(0x7f0800b7, float:1.8077872E38)
            return r2
        L_0x1118:
            r2 = 2131230901(0x7f0800b5, float:1.8077868E38)
            return r2
        L_0x111c:
            r2 = 2131230899(0x7f0800b3, float:1.8077864E38)
            return r2
        L_0x1120:
            r2 = 2131231041(0x7f080141, float:1.8078152E38)
            return r2
        L_0x1124:
            r2 = 2131230904(0x7f0800b8, float:1.8077874E38)
            return r2
        L_0x1128:
            r2 = 2131230895(0x7f0800af, float:1.8077856E38)
            return r2
        L_0x112c:
            r2 = 2131230900(0x7f0800b4, float:1.8077866E38)
            return r2
        L_0x1130:
            r2 = 2131230902(0x7f0800b6, float:1.807787E38)
            return r2
        L_0x1134:
            r2 = 2131230893(0x7f0800ad, float:1.8077852E38)
            return r2
        L_0x1138:
            r2 = 2131231118(0x7f08018e, float:1.8078308E38)
            return r2
        L_0x113c:
            r2 = 2131230898(0x7f0800b2, float:1.8077862E38)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils.getFlagDrawableResId(android.content.Context, com.jch.racWiFi.userManagement.countryCodeManager.Country):int");
    }

    public static List<Country> getAllCountries(Context context) {
        List<Country> list = countries;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        countries = arrayList;
        arrayList.add(new Country(R.string.country_afghanistan_code, R.string.country_afghanistan_number, R.string.country_afghanistan_name));
        countries.add(new Country(R.string.country_albania_code, R.string.country_albania_number, R.string.country_albania_name));
        countries.add(new Country(R.string.country_algeria_code, R.string.country_algeria_number, R.string.country_algeria_name));
        countries.add(new Country(R.string.country_andorra_code, R.string.country_andorra_number, R.string.country_andorra_name));
        countries.add(new Country(R.string.country_angola_code, R.string.country_angola_number, R.string.country_angola_name));
        countries.add(new Country(R.string.country_anguilla_code, R.string.country_anguilla_number, R.string.country_anguilla_name));
        countries.add(new Country(R.string.country_antarctica_code, R.string.country_antarctica_number, R.string.country_antarctica_name));
        countries.add(new Country(R.string.country_antigua_and_barbuda_code, R.string.country_antigua_and_barbuda_number, R.string.country_antigua_and_barbuda_name));
        countries.add(new Country(R.string.country_argentina_code, R.string.country_argentina_number, R.string.country_argentina_name));
        countries.add(new Country(R.string.country_armenia_code, R.string.country_armenia_number, R.string.country_armenia_name));
        countries.add(new Country(R.string.country_aruba_code, R.string.country_aruba_number, R.string.country_aruba_name));
        countries.add(new Country(R.string.country_australia_code, R.string.country_australia_number, R.string.country_australia_name));
        countries.add(new Country(R.string.country_austria_code, R.string.country_austria_number, R.string.country_austria_name));
        countries.add(new Country(R.string.country_azerbaijan_code, R.string.country_azerbaijan_number, R.string.country_azerbaijan_name));
        countries.add(new Country(R.string.country_bahamas_code, R.string.country_bahamas_number, R.string.country_bahamas_name));
        countries.add(new Country(R.string.country_bahrain_code, R.string.country_bahrain_number, R.string.country_bahrain_name));
        countries.add(new Country(R.string.country_bangladesh_code, R.string.country_bangladesh_number, R.string.country_bangladesh_name));
        countries.add(new Country(R.string.country_barbados_code, R.string.country_barbados_number, R.string.country_barbados_name));
        countries.add(new Country(R.string.country_belarus_code, R.string.country_belarus_number, R.string.country_belarus_name));
        countries.add(new Country(R.string.country_belgium_code, R.string.country_belgium_number, R.string.country_belgium_name));
        countries.add(new Country(R.string.country_belize_code, R.string.country_belize_number, R.string.country_belize_name));
        countries.add(new Country(R.string.country_benin_code, R.string.country_benin_number, R.string.country_benin_name));
        countries.add(new Country(R.string.country_bermuda_code, R.string.country_bermuda_number, R.string.country_bermuda_name));
        countries.add(new Country(R.string.country_bhutan_code, R.string.country_bhutan_number, R.string.country_bhutan_name));
        countries.add(new Country(R.string.country_bolivia_code, R.string.country_bolivia_number, R.string.country_bolivia_name));
        countries.add(new Country(R.string.country_bosnia_and_herzegovina_code, R.string.country_bosnia_and_herzegovina_number, R.string.country_bosnia_and_herzegovina_name));
        countries.add(new Country(R.string.country_botswana_code, R.string.country_botswana_number, R.string.country_botswana_name));
        countries.add(new Country(R.string.country_brazil_code, R.string.country_brazil_number, R.string.country_brazil_name));
        countries.add(new Country(R.string.country_british_virgin_islands_code, R.string.country_british_virgin_islands_number, R.string.country_british_virgin_islands_name));
        countries.add(new Country(R.string.country_brunei_darussalam_code, R.string.country_brunei_darussalam_number, R.string.country_brunei_darussalam_name));
        countries.add(new Country(R.string.country_bulgaria_code, R.string.country_bulgaria_number, R.string.country_bulgaria_name));
        countries.add(new Country(R.string.country_burkina_faso_code, R.string.country_burkina_faso_number, R.string.country_burkina_faso_name));
        countries.add(new Country(R.string.country_burundi_code, R.string.country_burundi_number, R.string.country_burundi_name));
        countries.add(new Country(R.string.country_cambodia_code, R.string.country_cambodia_number, R.string.country_cambodia_name));
        countries.add(new Country(R.string.country_cameroon_code, R.string.country_cameroon_number, R.string.country_cameroon_name));
        countries.add(new Country(R.string.country_canada_code, R.string.country_canada_number, R.string.country_canada_name));
        countries.add(new Country(R.string.country_cape_verde_code, R.string.country_cape_verde_number, R.string.country_cape_verde_name));
        countries.add(new Country(R.string.country_cayman_islands_code, R.string.country_cayman_islands_number, R.string.country_cayman_islands_name));
        countries.add(new Country(R.string.country_central_african_republic_code, R.string.country_central_african_republic_number, R.string.country_central_african_republic_name));
        countries.add(new Country(R.string.country_chad_code, R.string.country_chad_number, R.string.country_chad_name));
        countries.add(new Country(R.string.country_chile_code, R.string.country_chile_number, R.string.country_chile_name));
        countries.add(new Country(R.string.country_china_code, R.string.country_china_number, R.string.country_china_name));
        countries.add(new Country(R.string.country_christmas_island_code, R.string.country_christmas_island_number, R.string.country_christmas_island_name));
        countries.add(new Country(R.string.country_cocos_keeling_islands_code, R.string.country_cocos_keeling_islands_number, R.string.country_cocos_keeling_islands_name));
        countries.add(new Country(R.string.country_colombia_code, R.string.country_colombia_number, R.string.country_colombia_name));
        countries.add(new Country(R.string.country_comoros_code, R.string.country_comoros_number, R.string.country_comoros_name));
        countries.add(new Country(R.string.country_congo_code, R.string.country_congo_number, R.string.country_congo_name));
        countries.add(new Country(R.string.country_the_democratic_republic_of_congo_code, R.string.country_the_democratic_republic_of_congo_number, R.string.country_the_democratic_republic_of_congo_name));
        countries.add(new Country(R.string.country_cook_islands_code, R.string.country_cook_islands_number, R.string.country_cook_islands_name));
        countries.add(new Country(R.string.country_costa_rica_code, R.string.country_costa_rica_number, R.string.country_costa_rica_name));
        countries.add(new Country(R.string.country_croatia_code, R.string.country_croatia_number, R.string.country_croatia_name));
        countries.add(new Country(R.string.country_cuba_code, R.string.country_cuba_number, R.string.country_cuba_name));
        countries.add(new Country(R.string.country_cyprus_code, R.string.country_cyprus_number, R.string.country_cyprus_name));
        countries.add(new Country(R.string.country_czech_republic_code, R.string.country_czech_republic_number, R.string.country_czech_republic_name));
        countries.add(new Country(R.string.country_denmark_code, R.string.country_denmark_number, R.string.country_denmark_name));
        countries.add(new Country(R.string.country_djibouti_code, R.string.country_djibouti_number, R.string.country_djibouti_name));
        countries.add(new Country(R.string.country_dominica_code, R.string.country_dominica_number, R.string.country_dominica_name));
        countries.add(new Country(R.string.country_dominican_republic_code, R.string.country_dominican_republic_number, R.string.country_dominican_republic_name));
        countries.add(new Country(R.string.country_timor_leste_code, R.string.country_timor_leste_number, R.string.country_timor_leste_name));
        countries.add(new Country(R.string.country_ecuador_code, R.string.country_ecuador_number, R.string.country_ecuador_name));
        countries.add(new Country(R.string.country_egypt_code, R.string.country_egypt_number, R.string.country_egypt_name));
        countries.add(new Country(R.string.country_el_salvador_code, R.string.country_el_salvador_number, R.string.country_el_salvador_name));
        countries.add(new Country(R.string.country_equatorial_guinea_code, R.string.country_equatorial_guinea_number, R.string.country_equatorial_guinea_name));
        countries.add(new Country(R.string.country_eritrea_code, R.string.country_eritrea_number, R.string.country_eritrea_name));
        countries.add(new Country(R.string.country_estonia_code, R.string.country_estonia_number, R.string.country_estonia_name));
        countries.add(new Country(R.string.country_ethiopia_code, R.string.country_ethiopia_number, R.string.country_ethiopia_name));
        countries.add(new Country(R.string.country_falkland_islands_malvinas_code, R.string.country_falkland_islands_malvinas_number, R.string.country_falkland_islands_malvinas_name));
        countries.add(new Country(R.string.country_faroe_islands_code, R.string.country_faroe_islands_number, R.string.country_faroe_islands_name));
        countries.add(new Country(R.string.country_fiji_code, R.string.country_fiji_number, R.string.country_fiji_name));
        countries.add(new Country(R.string.country_finland_code, R.string.country_finland_number, R.string.country_finland_name));
        countries.add(new Country(R.string.country_france_code, R.string.country_france_number, R.string.country_france_name));
        countries.add(new Country(R.string.country_french_guyana_code, R.string.country_french_guyana_number, R.string.country_french_guyana_name));
        countries.add(new Country(R.string.country_french_polynesia_code, R.string.country_french_polynesia_number, R.string.country_french_polynesia_name));
        countries.add(new Country(R.string.country_gabon_code, R.string.country_gabon_number, R.string.country_gabon_name));
        countries.add(new Country(R.string.country_gambia_code, R.string.country_gambia_number, R.string.country_gambia_name));
        countries.add(new Country(R.string.country_georgia_code, R.string.country_georgia_number, R.string.country_georgia_name));
        countries.add(new Country(R.string.country_germany_code, R.string.country_germany_number, R.string.country_germany_name));
        countries.add(new Country(R.string.country_ghana_code, R.string.country_ghana_number, R.string.country_ghana_name));
        countries.add(new Country(R.string.country_gibraltar_code, R.string.country_gibraltar_number, R.string.country_gibraltar_name));
        countries.add(new Country(R.string.country_greece_code, R.string.country_greece_number, R.string.country_greece_name));
        countries.add(new Country(R.string.country_greenland_code, R.string.country_greenland_number, R.string.country_greenland_name));
        countries.add(new Country(R.string.country_grenada_code, R.string.country_grenada_number, R.string.country_grenada_name));
        countries.add(new Country(R.string.country_guatemala_code, R.string.country_guatemala_number, R.string.country_guatemala_name));
        countries.add(new Country(R.string.country_guinea_code, R.string.country_guinea_number, R.string.country_guinea_name));
        countries.add(new Country(R.string.country_guinea_bissau_code, R.string.country_guinea_bissau_number, R.string.country_guinea_bissau_name));
        countries.add(new Country(R.string.country_guyana_code, R.string.country_guyana_number, R.string.country_guyana_name));
        countries.add(new Country(R.string.country_haiti_code, R.string.country_haiti_number, R.string.country_haiti_name));
        countries.add(new Country(R.string.country_honduras_code, R.string.country_honduras_number, R.string.country_honduras_name));
        countries.add(new Country(R.string.country_hong_kong_code, R.string.country_hong_kong_number, R.string.country_hong_kong_name));
        countries.add(new Country(R.string.country_hungary_code, R.string.country_hungary_number, R.string.country_hungary_name));
        countries.add(new Country(R.string.country_iceland_code, R.string.country_iceland_number, R.string.country_iceland_name));
        countries.add(new Country(R.string.country_india_code, R.string.country_india_number, R.string.country_india_name));
        countries.add(new Country(R.string.country_indonesia_code, R.string.country_indonesia_number, R.string.country_indonesia_name));
        countries.add(new Country(R.string.country_iran_code, R.string.country_iran_number, R.string.country_iran_name));
        countries.add(new Country(R.string.country_iraq_code, R.string.country_iraq_number, R.string.country_iraq_name));
        countries.add(new Country(R.string.country_ireland_code, R.string.country_ireland_number, R.string.country_ireland_name));
        countries.add(new Country(R.string.country_isle_of_man_code, R.string.country_isle_of_man_number, R.string.country_isle_of_man_name));
        countries.add(new Country(R.string.country_israel_code, R.string.country_israel_number, R.string.country_israel_name));
        countries.add(new Country(R.string.country_italy_code, R.string.country_italy_number, R.string.country_italy_name));
        countries.add(new Country(R.string.country_cote_d_ivoire_code, R.string.country_cote_d_ivoire_number, R.string.country_cote_d_ivoire_name));
        countries.add(new Country(R.string.country_jamaica_code, R.string.country_jamaica_number, R.string.country_jamaica_name));
        countries.add(new Country(R.string.country_japan_code, R.string.country_japan_number, R.string.country_japan_name));
        countries.add(new Country(R.string.country_jordan_code, R.string.country_jordan_number, R.string.country_jordan_name));
        countries.add(new Country(R.string.country_kazakhstan_code, R.string.country_kazakhstan_number, R.string.country_kazakhstan_name));
        countries.add(new Country(R.string.country_kenya_code, R.string.country_kenya_number, R.string.country_kenya_name));
        countries.add(new Country(R.string.country_kiribati_code, R.string.country_kiribati_number, R.string.country_kiribati_name));
        countries.add(new Country(R.string.country_kosovo_code, R.string.country_kosovo_number, R.string.country_kosovo_name));
        countries.add(new Country(R.string.country_kuwait_code, R.string.country_kuwait_number, R.string.country_kuwait_name));
        countries.add(new Country(R.string.country_kyrgyzstan_code, R.string.country_kyrgyzstan_number, R.string.country_kyrgyzstan_name));
        countries.add(new Country(R.string.country_lao_peoples_democratic_republic_code, R.string.country_lao_peoples_democratic_republic_number, R.string.country_lao_peoples_democratic_republic_name));
        countries.add(new Country(R.string.country_latvia_code, R.string.country_latvia_number, R.string.country_latvia_name));
        countries.add(new Country(R.string.country_lebanon_code, R.string.country_lebanon_number, R.string.country_lebanon_name));
        countries.add(new Country(R.string.country_lesotho_code, R.string.country_lesotho_number, R.string.country_lesotho_name));
        countries.add(new Country(R.string.country_liberia_code, R.string.country_liberia_number, R.string.country_liberia_name));
        countries.add(new Country(R.string.country_libya_code, R.string.country_libya_number, R.string.country_libya_name));
        countries.add(new Country(R.string.country_liechtenstein_code, R.string.country_liechtenstein_number, R.string.country_liechtenstein_name));
        countries.add(new Country(R.string.country_lithuania_code, R.string.country_lithuania_number, R.string.country_lithuania_name));
        countries.add(new Country(R.string.country_luxembourg_code, R.string.country_luxembourg_number, R.string.country_luxembourg_name));
        countries.add(new Country(R.string.country_macao_code, R.string.country_macao_number, R.string.country_macao_name));
        countries.add(new Country(R.string.country_macedonia_code, R.string.country_macedonia_number, R.string.country_macedonia_name));
        countries.add(new Country(R.string.country_madagascar_code, R.string.country_madagascar_number, R.string.country_madagascar_name));
        countries.add(new Country(R.string.country_malawi_code, R.string.country_malawi_number, R.string.country_malawi_name));
        countries.add(new Country(R.string.country_malaysia_code, R.string.country_malaysia_number, R.string.country_malaysia_name));
        countries.add(new Country(R.string.country_maldives_code, R.string.country_maldives_number, R.string.country_maldives_name));
        countries.add(new Country(R.string.country_mali_code, R.string.country_mali_number, R.string.country_mali_name));
        countries.add(new Country(R.string.country_malta_code, R.string.country_malta_number, R.string.country_malta_name));
        countries.add(new Country(R.string.country_marshall_islands_code, R.string.country_marshall_islands_number, R.string.country_marshall_islands_name));
        countries.add(new Country(R.string.country_martinique_code, R.string.country_martinique_number, R.string.country_martinique_name));
        countries.add(new Country(R.string.country_mauritania_code, R.string.country_mauritania_number, R.string.country_mauritania_name));
        countries.add(new Country(R.string.country_mauritius_code, R.string.country_mauritius_number, R.string.country_mauritius_name));
        countries.add(new Country(R.string.country_mayotte_code, R.string.country_mayotte_number, R.string.country_mayotte_name));
        countries.add(new Country(R.string.country_mexico_code, R.string.country_mexico_number, R.string.country_mexico_name));
        countries.add(new Country(R.string.country_micronesia_code, R.string.country_micronesia_number, R.string.country_micronesia_name));
        countries.add(new Country(R.string.country_moldova_code, R.string.country_moldova_number, R.string.country_moldova_name));
        countries.add(new Country(R.string.country_monaco_code, R.string.country_monaco_number, R.string.country_monaco_name));
        countries.add(new Country(R.string.country_mongolia_code, R.string.country_mongolia_number, R.string.country_mongolia_name));
        countries.add(new Country(R.string.country_montserrat_code, R.string.country_montserrat_number, R.string.country_montserrat_name));
        countries.add(new Country(R.string.country_montenegro_code, R.string.country_montenegro_number, R.string.country_montenegro_name));
        countries.add(new Country(R.string.country_morocco_code, R.string.country_morocco_number, R.string.country_morocco_name));
        countries.add(new Country(R.string.country_myanmar_code, R.string.country_myanmar_number, R.string.country_myanmar_name));
        countries.add(new Country(R.string.country_mozambique_code, R.string.country_mozambique_number, R.string.country_mozambique_name));
        countries.add(new Country(R.string.country_namibia_code, R.string.country_namibia_number, R.string.country_namibia_name));
        countries.add(new Country(R.string.country_nauru_code, R.string.country_nauru_number, R.string.country_nauru_name));
        countries.add(new Country(R.string.country_nepal_code, R.string.country_nepal_number, R.string.country_nepal_name));
        countries.add(new Country(R.string.country_netherlands_code, R.string.country_netherlands_number, R.string.country_netherlands_name));
        countries.add(new Country(R.string.country_netherlands_antilles_code, R.string.country_netherlands_antilles_number, R.string.country_netherlands_antilles_name));
        countries.add(new Country(R.string.country_new_caledonia_code, R.string.country_new_caledonia_number, R.string.country_new_caledonia_name));
        countries.add(new Country(R.string.country_new_zealand_code, R.string.country_new_zealand_number, R.string.country_new_zealand_name));
        countries.add(new Country(R.string.country_nicaragua_code, R.string.country_nicaragua_number, R.string.country_nicaragua_name));
        countries.add(new Country(R.string.country_niger_code, R.string.country_niger_number, R.string.country_niger_name));
        countries.add(new Country(R.string.country_nigeria_code, R.string.country_nigeria_number, R.string.country_nigeria_name));
        countries.add(new Country(R.string.country_niue_code, R.string.country_niue_number, R.string.country_niue_name));
        countries.add(new Country(R.string.country_north_korea_code, R.string.country_north_korea_number, R.string.country_north_korea_name));
        countries.add(new Country(R.string.country_norway_code, R.string.country_norway_number, R.string.country_norway_name));
        countries.add(new Country(R.string.country_oman_code, R.string.country_oman_number, R.string.country_oman_name));
        countries.add(new Country(R.string.country_pakistan_code, R.string.country_pakistan_number, R.string.country_pakistan_name));
        countries.add(new Country(R.string.country_palau_code, R.string.country_palau_number, R.string.country_palau_name));
        countries.add(new Country(R.string.country_panama_code, R.string.country_panama_number, R.string.country_panama_name));
        countries.add(new Country(R.string.country_papua_new_guinea_code, R.string.country_papua_new_guinea_number, R.string.country_papua_new_guinea_name));
        countries.add(new Country(R.string.country_paraguay_code, R.string.country_paraguay_number, R.string.country_paraguay_name));
        countries.add(new Country(R.string.country_peru_code, R.string.country_peru_number, R.string.country_peru_name));
        countries.add(new Country(R.string.country_philippines_code, R.string.country_philippines_number, R.string.country_philippines_name));
        countries.add(new Country(R.string.country_pitcairn_code, R.string.country_pitcairn_number, R.string.country_pitcairn_name));
        countries.add(new Country(R.string.country_poland_code, R.string.country_poland_number, R.string.country_poland_name));
        countries.add(new Country(R.string.country_portugal_code, R.string.country_portugal_number, R.string.country_portugal_name));
        countries.add(new Country(R.string.country_puerto_rico_code, R.string.country_puerto_rico_number, R.string.country_puerto_rico_name));
        countries.add(new Country(R.string.country_qatar_code, R.string.country_qatar_number, R.string.country_qatar_name));
        countries.add(new Country(R.string.country_reunion_code, R.string.country_reunion_number, R.string.country_reunion_name));
        countries.add(new Country(R.string.country_romania_code, R.string.country_romania_number, R.string.country_romania_name));
        countries.add(new Country(R.string.country_russian_federation_code, R.string.country_russian_federation_number, R.string.country_russian_federation_name));
        countries.add(new Country(R.string.country_rwanda_code, R.string.country_rwanda_number, R.string.country_rwanda_name));
        countries.add(new Country(R.string.country_saint_barthelemy_code, R.string.country_saint_barthelemy_number, R.string.country_saint_barthelemy_name));
        countries.add(new Country(R.string.country_saint_kitts_and_nevis_code, R.string.country_saint_kitts_and_nevis_number, R.string.country_saint_kitts_and_nevis_name));
        countries.add(new Country(R.string.country_saint_lucia_code, R.string.country_saint_lucia_number, R.string.country_saint_lucia_name));
        countries.add(new Country(R.string.country_saint_vincent_the_grenadines_code, R.string.country_saint_vincent_the_grenadines_number, R.string.country_saint_vincent_the_grenadines_name));
        countries.add(new Country(R.string.country_samoa_code, R.string.country_samoa_number, R.string.country_samoa_name));
        countries.add(new Country(R.string.country_san_marino_code, R.string.country_san_marino_number, R.string.country_san_marino_name));
        countries.add(new Country(R.string.country_sao_tome_and_principe_code, R.string.country_sao_tome_and_principe_number, R.string.country_sao_tome_and_principe_name));
        countries.add(new Country(R.string.country_saudi_arabia_code, R.string.country_saudi_arabia_number, R.string.country_saudi_arabia_name));
        countries.add(new Country(R.string.country_senegal_code, R.string.country_senegal_number, R.string.country_senegal_name));
        countries.add(new Country(R.string.country_serbia_code, R.string.country_serbia_number, R.string.country_serbia_name));
        countries.add(new Country(R.string.country_seychelles_code, R.string.country_seychelles_number, R.string.country_seychelles_name));
        countries.add(new Country(R.string.country_sierra_leone_code, R.string.country_sierra_leone_number, R.string.country_sierra_leone_name));
        countries.add(new Country(R.string.country_singapore_code, R.string.country_singapore_number, R.string.country_singapore_name));
        countries.add(new Country(R.string.country_sint_maarten_code, R.string.country_sint_maarten_number, R.string.country_sint_maarten_name));
        countries.add(new Country(R.string.country_slovakia_code, R.string.country_slovakia_number, R.string.country_slovakia_name));
        countries.add(new Country(R.string.country_slovenia_code, R.string.country_slovenia_number, R.string.country_slovenia_name));
        countries.add(new Country(R.string.country_solomon_islands_code, R.string.country_solomon_islands_number, R.string.country_solomon_islands_name));
        countries.add(new Country(R.string.country_somalia_code, R.string.country_somalia_number, R.string.country_somalia_name));
        countries.add(new Country(R.string.country_south_africa_code, R.string.country_south_africa_number, R.string.country_south_africa_name));
        countries.add(new Country(R.string.country_south_korea_code, R.string.country_south_korea_number, R.string.country_south_korea_name));
        countries.add(new Country(R.string.country_spain_code, R.string.country_spain_number, R.string.country_spain_name));
        countries.add(new Country(R.string.country_sri_lanka_code, R.string.country_sri_lanka_number, R.string.country_sri_lanka_name));
        countries.add(new Country(R.string.country_saint_helena_code, R.string.country_saint_helena_number, R.string.country_saint_helena_name));
        countries.add(new Country(R.string.country_saint_pierre_and_miquelon_code, R.string.country_saint_pierre_and_miquelon_number, R.string.country_saint_pierre_and_miquelon_name));
        countries.add(new Country(R.string.country_south_sudan_code, R.string.country_south_sudan_number, R.string.country_south_sudan_name));
        countries.add(new Country(R.string.country_sudan_code, R.string.country_sudan_number, R.string.country_sudan_name));
        countries.add(new Country(R.string.country_suriname_code, R.string.country_suriname_number, R.string.country_suriname_name));
        countries.add(new Country(R.string.country_swaziland_code, R.string.country_swaziland_number, R.string.country_swaziland_name));
        countries.add(new Country(R.string.country_sweden_code, R.string.country_sweden_number, R.string.country_sweden_name));
        countries.add(new Country(R.string.country_switzerland_code, R.string.country_switzerland_number, R.string.country_switzerland_name));
        countries.add(new Country(R.string.country_syrian_arab_republic_code, R.string.country_syrian_arab_republic_number, R.string.country_syrian_arab_republic_name));
        countries.add(new Country(R.string.country_taiwan_code, R.string.country_taiwan_number, R.string.country_taiwan_name));
        countries.add(new Country(R.string.country_tajikistan_code, R.string.country_tajikistan_number, R.string.country_tajikistan_name));
        countries.add(new Country(R.string.country_tanzania_code, R.string.country_tanzania_number, R.string.country_tanzania_name));
        countries.add(new Country(R.string.country_thailand_code, R.string.country_thailand_number, R.string.country_thailand_name));
        countries.add(new Country(R.string.country_togo_code, R.string.country_togo_number, R.string.country_togo_name));
        countries.add(new Country(R.string.country_tokelau_code, R.string.country_tokelau_number, R.string.country_tokelau_name));
        countries.add(new Country(R.string.country_tonga_code, R.string.country_tonga_number, R.string.country_tonga_name));
        countries.add(new Country(R.string.country_trinidad_tobago_code, R.string.country_trinidad_tobago_number, R.string.country_trinidad_tobago_name));
        countries.add(new Country(R.string.country_tunisia_code, R.string.country_tunisia_number, R.string.country_tunisia_name));
        countries.add(new Country(R.string.country_turkey_code, R.string.country_turkey_number, R.string.country_turkey_name));
        countries.add(new Country(R.string.country_turkmenistan_code, R.string.country_turkmenistan_number, R.string.country_turkmenistan_name));
        countries.add(new Country(R.string.country_turks_and_caicos_islands_code, R.string.country_turks_and_caicos_islands_number, R.string.country_turks_and_caicos_islands_name));
        countries.add(new Country(R.string.country_tuvalu_code, R.string.country_tuvalu_number, R.string.country_tuvalu_name));
        countries.add(new Country(R.string.country_united_arab_emirates_code, R.string.country_united_arab_emirates_number, R.string.country_united_arab_emirates_name));
        countries.add(new Country(R.string.country_uganda_code, R.string.country_uganda_number, R.string.country_uganda_name));
        countries.add(new Country(R.string.country_united_kingdom_code, R.string.country_united_kingdom_number, R.string.country_united_kingdom_name));
        countries.add(new Country(R.string.country_ukraine_code, R.string.country_ukraine_number, R.string.country_ukraine_name));
        countries.add(new Country(R.string.country_uruguay_code, R.string.country_uruguay_number, R.string.country_uruguay_name));
        countries.add(new Country(R.string.country_united_states_code, R.string.country_united_states_number, R.string.country_united_states_name));
        countries.add(new Country(R.string.country_us_virgin_islands_code, R.string.country_us_virgin_islands_number, R.string.country_us_virgin_islands_name));
        countries.add(new Country(R.string.country_uzbekistan_code, R.string.country_uzbekistan_number, R.string.country_uzbekistan_name));
        countries.add(new Country(R.string.country_vanuatu_code, R.string.country_vanuatu_number, R.string.country_vanuatu_name));
        countries.add(new Country(R.string.country_holy_see_vatican_city_state_code, R.string.country_holy_see_vatican_city_state_number, R.string.country_holy_see_vatican_city_state_name));
        countries.add(new Country(R.string.country_venezuela_code, R.string.country_venezuela_number, R.string.country_venezuela_name));
        countries.add(new Country(R.string.country_viet_nam_code, R.string.country_viet_nam_number, R.string.country_viet_nam_name));
        countries.add(new Country(R.string.country_wallis_and_futuna_code, R.string.country_wallis_and_futuna_number, R.string.country_wallis_and_futuna_name));
        countries.add(new Country(R.string.country_yemen_code, R.string.country_yemen_number, R.string.country_yemen_name));
        countries.add(new Country(R.string.country_zambia_code, R.string.country_zambia_number, R.string.country_zambia_name));
        countries.add(new Country(R.string.country_zimbabwe_code, R.string.country_zimbabwe_number, R.string.country_zimbabwe_name));
        countries.add(new Country(R.string.country_aland_islands_code, R.string.country_aland_islands_number, R.string.country_aland_islands_name));
        countries.add(new Country(R.string.country_american_samoa_code, R.string.country_american_samoa_number, R.string.country_american_samoa_name));
        countries.add(new Country(R.string.country_british_indian_ocean_territory_code, R.string.country_british_indian_ocean_territory_number, R.string.country_british_indian_ocean_territory_name));
        countries.add(new Country(R.string.country_guadeloupe_code, R.string.country_guadeloupe_number, R.string.country_guadeloupe_name));
        countries.add(new Country(R.string.country_guam_code, R.string.country_guam_number, R.string.country_guam_name));
        countries.add(new Country(R.string.country_guernsey_code, R.string.country_guernsey_number, R.string.country_guernsey_name));
        countries.add(new Country(R.string.country_jersey_code, R.string.country_jersey_number, R.string.country_jersey_name));
        countries.add(new Country(R.string.country_norfolk_island_code, R.string.country_norfolk_island_number, R.string.country_norfolk_island_name));
        countries.add(new Country(R.string.country_northern_mariana_islands_code, R.string.country_northern_mariana_islands_number, R.string.country_northern_mariana_islands_name));
        countries.add(new Country(R.string.country_palestian_territory_code, R.string.country_palestian_territory_number, R.string.country_palestian_territory_name));
        countries.add(new Country(R.string.country_saint_martin_code, R.string.country_saint_martin_number, R.string.country_saint_martin_name));
        countries.add(new Country(R.string.country_south_georgia_code, R.string.country_south_georgia_number, R.string.country_south_georgia_name));
        return countries;
    }

    static Country getByNumber(Context context, List<Country> list, String str) {
        if (str.length() == 0) {
            return null;
        }
        int i = 0;
        if (str.charAt(0) == '+') {
            i = 1;
        }
        for (int i2 = i; i2 < i + 4; i2++) {
            Country byCode = getByCode(context, list, str.substring(i, i2));
            if (byCode != null) {
                return byCode;
            }
        }
        return null;
    }

    static Country getByCode(Context context, List<Country> list, int i) {
        return getByCode(context, list, i + "");
    }

    public static Country getByCode(Context context, List<Country> list, String str) {
        if (str != null && str.startsWith(Marker.ANY_NON_NULL_MARKER)) {
            str = str.substring(1);
        }
        if (list != null && !list.isEmpty()) {
            for (Country next : list) {
                if (context.getString(next.getPhoneCode()).equals(str)) {
                    return next;
                }
            }
        }
        for (Country next2 : getAllCountries(context)) {
            if (context.getString(next2.getPhoneCode()).equals(str)) {
                return next2;
            }
        }
        return null;
    }

    public static Country getByNameCodeFromCustomCountries(Context context, List<Country> list, String str) {
        if (list == null || list.size() == 0) {
            return getByNameCodeFromAllCountries(context, str);
        }
        for (Country next : list) {
            if (context.getString(next.getIso()).equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    private static Country getByNameCodeFromAllCountries(Context context, String str) {
        for (Country next : getAllCountries(context)) {
            if (context.getString(next.getIso()).equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    static List<String> getCountryIsoByTimeZone(Context context, String str) {
        return getTimeZoneAndCountryISOs(context).get(str);
    }

    private static Map<String, List<String>> getTimeZoneAndCountryISOs(Context context) {
        Map<String, List<String>> map = timeZoneAndCountryISOs;
        if (map != null && !map.isEmpty()) {
            return timeZoneAndCountryISOs;
        }
        timeZoneAndCountryISOs = new HashMap();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.zone1970)));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("\t");
                if (!readLine.substring(0, 1).contains("#") && split.length >= 3) {
                    ArrayList arrayList = new ArrayList();
                    Collections.addAll(arrayList, split[0].split(","));
                    timeZoneAndCountryISOs.put(split[2], arrayList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return timeZoneAndCountryISOs;
    }

    public static class ProhibitedCountryUtils {
        private static final List<String> prohibitedCountryList;

        static {
            ArrayList arrayList = new ArrayList();
            prohibitedCountryList = arrayList;
            arrayList.add("China");
            arrayList.add("Crimea");
            arrayList.add("Cuba");
            arrayList.add("Iran");
            arrayList.add("North Korea");
            arrayList.add("Sudan");
            arrayList.add("Syrian Arab Republic");
            arrayList.add("Vietnam");
        }

        public static boolean isCountryProhibited(String str) {
            String lowerCase = str.toLowerCase();
            String upperCase = str.toUpperCase();
            List<String> list = prohibitedCountryList;
            return list.contains(str) || list.contains(lowerCase) || list.contains(upperCase);
        }
    }
}
