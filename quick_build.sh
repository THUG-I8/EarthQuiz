#!/bin/bash

echo "🚀 بدء بناء تطبيق EarthQuiz..."

# منح صلاحيات التنفيذ
chmod +x gradlew

# تنظيف المشروع
echo "🧹 تنظيف الملفات السابقة..."
./gradlew clean

# بناء APK
echo "🔨 بناء APK..."
./gradlew assembleDebug

# التحقق من نجاح البناء
if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    echo "✅ تم بناء APK بنجاح!"
    echo "📁 مسار الملف: app/build/outputs/apk/debug/app-debug.apk"
    
    # عرض حجم الملف
    size=$(du -h app/build/outputs/apk/debug/app-debug.apk | cut -f1)
    echo "📊 حجم الملف: $size"
    
    echo "🎉 التطبيق جاهز للتثبيت!"
else
    echo "❌ فشل في بناء APK. تحقق من الأخطاء أعلاه."
fi