# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep Gson classes
-keep class com.google.gson.** { *; }
-keep class com.earth.quiz.data.** { *; }

# Keep Compose
-keep class androidx.compose.** { *; }

# Keep coroutines
-keep class kotlinx.coroutines.** { *; }

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile