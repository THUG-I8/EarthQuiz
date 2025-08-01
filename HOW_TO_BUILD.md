# 📱 كيفية الحصول على APK تطبيق EarthQuiz

## ⚡ الطريقة السريعة (أوامر مباشرة):

### لنظام Linux/Mac:
```bash
./quick_build.sh
```

### لنظام Windows:
```
quick_build.bat
```

---

## 🔧 الطريقة اليدوية:

### 1. افتح Terminal/Command Prompt
### 2. اذهب لمجلد المشروع:
```bash
cd EarthQuiz
```

### 3. شغل الأوامر التالية واحد تلو الآخر:

#### لنظام Linux/Mac:
```bash
chmod +x gradlew
./gradlew clean
./gradlew assembleDebug
```

#### لنظام Windows:
```
gradlew.bat clean
gradlew.bat assembleDebug
```

### 4. ابحث عن ملف APK في:
```
app/build/outputs/apk/debug/app-debug.apk
```

---

## 📲 كيفية التثبيت:

1. **انسخ ملف APK لهاتفك**
2. **فعّل "المصادر غير المعروفة"** في إعدادات الأمان
3. **انقر على ملف APK** لتثبيته
4. **استمتع بالتطبيق!** 🎉

---

## ❓ إذا واجهت مشاكل:

- **تأكد من وجود Java 17 أو أحدث**
- **تأكد من اتصال الإنترنت** (لتحميل التبعيات)
- **راجع ملف BUILD_INSTRUCTIONS.md** للتفاصيل الكاملة

**🎯 بعد اتباع هذه الخطوات، ستحصل على APK جاهز للتثبيت!**