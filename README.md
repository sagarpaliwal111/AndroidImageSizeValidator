APK Image Size Checker Gradle Task

Overview

This custom Gradle task helps you identify oversized images in your Android APK to keep your app size lean and efficient.

Large drawable assets can silently bloat your APK size, leading to longer downloads, slower installs, and unhappy users. This task automates the detection of such large images during your build process, so you catch them early.

Features

Scans APK for image files (.png, .jpg, .jpeg, .webp, .gif, and .xml drawables)
Counts total images found inside the APK
Flags images exceeding a size threshold (default: 80 KB)
Lists oversized images with exact sizes and excess amount
Reports potential size savings if images are optimized
Runs at build time — no more surprises during QA or release
Easily integrated into existing Gradle build scripts


Usage

1: Add the task to your build.gradle (app-level) file.


2:Adjust the APK path and maximum size limit as needed within the task.

3:Run the task from the command line.

= ./gradlew checkImageSizesInApk


OUTPUT
Check the console output for:
Total images found
List of images exceeding the size limit
Potential savings if oversized images are optimized


Why Use This?

Prevent app size bloat caused by unoptimized image assets.
Catch oversized images before shipping your app.
Integrate easily into CI/CD pipelines for automated checks.
Save bandwidth and storage for your users.

Pro Tips

Set up the task to fail the build when oversized images are detected by adding a conditional throw inside the task.
Customize supported image formats or size thresholds to fit your project needs.
Combine with image optimization tools like ImageMagick or Android Studio’s built-in asset compression.
Contribution

Feel free to fork, modify, or suggest improvements!

Contact
If you want the full snippet or a demo, DM me or open an issue.

#AndroidDev #Gradle #BuildTools #APKSizeMatters #MobilePerformance #DevTips #Kotlin #ModernAndroidDev
