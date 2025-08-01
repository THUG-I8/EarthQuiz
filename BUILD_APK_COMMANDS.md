# 🚀 أوامر بناء APK بشكل مباشر

## الأوامر المطلوبة للحصول على APK جاهز:

### 1. تحضير المشروع
```bash
# التأكد من وجود صلاحيات التنفيذ
chmod +x gradlew

# تنظيف أي ملفات سابقة
./gradlew clean
```

### 2. بناء APK للاختبار (Debug)
```bash
./gradlew assembleDebug
```

### 3. العثور على ملف APK
```bash
ls app/build/outputs/apk/debug/
```
**الملف سيكون اسمه:** `app-debug.apk`

### 4. (اختياري) بناء APK للنشر (Release)
```bash
./gradlew assembleRelease
```

### 5. (اختياري) تثبيت مباشر على جهاز متصل
```bash
./gradlew installDebug
```

---

## مسار الملف النهائي:
```
app/build/outputs/apk/debug/app-debug.apk
```

## حجم الملف المتوقع:
تقريباً **15-25 MB**

## لتشغيل الأوامر:
1. افتح Terminal/Command Prompt
2. اذهب لمجلد المشروع: `cd EarthQuiz`
3. شغل الأوامر واحد تلو الآخر

**🎯 بعد تشغيل هذه الأوامر، ستحصل على APK جاهز للتنزيل والتثبيت!**