@echo off
echo 🚀 بدء بناء تطبيق EarthQuiz...

echo 🧹 تنظيف الملفات السابقة...
gradlew.bat clean

echo 🔨 بناء APK...
gradlew.bat assembleDebug

if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo ✅ تم بناء APK بنجاح!
    echo 📁 مسار الملف: app\build\outputs\apk\debug\app-debug.apk
    echo 🎉 التطبيق جاهز للتثبيت!
) else (
    echo ❌ فشل في بناء APK. تحقق من الأخطاء أعلاه.
)

pause