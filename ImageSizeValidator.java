tasks.register("checkImageSizesInApk") {.
group = "verification"
description = "Check if images in the APK are under 100KB and count total images."
doLast {
    // Define APK path and output directory
    def apkPath = ""
    def outputDir = file("${buildDir}/extractedReleaseApk")
    
    def maxSizeKB = 80
    def largeImages = []
    def totalImageCount = 0
    def totalSizeExceeding = 0
    
    // Define supported image extensions
    def imageExtensions = [".png", ".jpg", ".jpeg", ".webp", ".xml", ".gif"] as String[]

    def apkFile = file(apkPath)
    if (!apkFile.exists()) {
        throw new GradleException("APK not found at ${apkPath}. Build the APK first.")
    }

    delete outputDir
    outputDir.mkdirs()

    copy {
        from zipTree(apkFile)
        into outputDir
    }

    outputDir.eachFileRecurse { file ->
        if (file.isFile() && file.name.endsWithAny(imageExtensions)) {
            totalImageCount++
            def fileSizeKB = (file.length() / 1024).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP)
            
            // Check if file size exceeds the maximum limit
            if (fileSizeKB > maxSizeKB) {
                def excessSize = fileSizeKB - maxSizeKB
                totalSizeExceeding += excessSize
                largeImages.add([name: file.name, size: fileSizeKB, excess: excessSize])
            }
        }
    }

    // Print total image count
    println "Total images found in APK: ${totalImageCount}\n"

    // Print details of oversized images
    if (!largeImages.isEmpty()) {
        largeImages.sort { -it.size }
        println "Total images exceeding ${maxSizeKB}KB: ${largeImages.size()}\n"
        println "The following images exceed ${maxSizeKB}KB (Sorted by size):\n"
        largeImages.each { img ->
            println "   - ${img.name}: ${img.size}KB (Exceeds by ${img.excess}KB)"
        }

        // Calculate and print potential size savings
        def savedSizeMB = (totalSizeExceeding / 1024).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN).toLong()
        def savedSizeKB = (totalSizeExceeding.remainder(1024)).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN).toLong()
        println "Potential savings if all images are reduced under ${maxSizeKB}KB: ~${savedSizeMB}MB ${savedSizeKB}KB\n"
    } else {
        println "All images in the APK are within the allowed size limit."
    }
}
}
tasks.register("checkImageSizesInApk") {.
group = "verification"
description = "Check if images in the APK are under 100KB and count total images."
doLast {
    // Define APK path and output directory
    def apkPath = ""
    def outputDir = file("${buildDir}/extractedReleaseApk")
    
    // Set image size limit and initialize tracking variables
    def maxSizeKB = 100
    def largeImages = []
    def totalImageCount = 0
    def totalSizeExceeding = 0
    
    // Define supported image extensions
    def imageExtensions = [".png", ".jpg", ".jpeg", ".webp", ".xml", ".gif"] as String[]

    // Validate if the APK exists
    def apkFile = file(apkPath)
    if (!apkFile.exists()) {
        throw new GradleException("APK not found at ${apkPath}. Build the APK first.")
    }

    // Clean and create output directory
    delete outputDir
    outputDir.mkdirs()

    // Extract APK contents to the output directory
    copy {
        from zipTree(apkFile)
        into outputDir
    }

    // Iterate through extracted files to check image sizes
    outputDir.eachFileRecurse { file ->
        if (file.isFile() && file.name.endsWithAny(imageExtensions)) {
            totalImageCount++
            def fileSizeKB = (file.length() / 1024).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP)
            
            // Check if file size exceeds the maximum limit
            if (fileSizeKB > maxSizeKB) {
                def excessSize = fileSizeKB - maxSizeKB
                totalSizeExceeding += excessSize
                largeImages.add([name: file.name, size: fileSizeKB, excess: excessSize])
            }
        }
    }

    // Print total image count
    println "Total images found in APK: ${totalImageCount}\n"

    // Print details of oversized images
    if (!largeImages.isEmpty()) {
        largeImages.sort { -it.size }
        println "Total images exceeding ${maxSizeKB}KB: ${largeImages.size()}\n"
        println "The following images exceed ${maxSizeKB}KB (Sorted by size):\n"
        largeImages.each { img ->
            println "   - ${img.name}: ${img.size}KB (Exceeds by ${img.excess}KB)"
        }

        // Calculate and print potential size savings
        def savedSizeMB = (totalSizeExceeding / 1024).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN).toLong()
        def savedSizeKB = (totalSizeExceeding.remainder(1024)).toBigDecimal().setScale(0, BigDecimal.ROUND_DOWN).toLong()
        println "Potential savings if all images are reduced under ${maxSizeKB}KB: ~${savedSizeMB}MB ${savedSizeKB}KB\n"
    } else {
        println "All images in the APK are within the allowed size limit."
    }
}
}


