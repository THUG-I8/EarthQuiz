# 🔧 تعليمات بناء تطبيق EarthQuiz

## 📋 المتطلبات المسبقة

### النظام المطلوب
- **نظام التشغيل**: Windows 10+، macOS 10.14+، أو Linux
- **Java**: JDK 17 أو أحدث
- **الذاكرة**: 8 GB RAM على الأقل (16 GB مُوصى به)
- **التخزين**: 10 GB مساحة فارغة على الأقل

### أدوات التطوير
- **Android Studio**: الإصدار الأحدث (Hedgehog 2023.1.1 أو أحدث)
- **Android SDK**: API Level 34 مع Build Tools 34.0.0
- **Git**: لإدارة الكود المصدري

## 🚀 خطوات التثبيت والبناء

### 1. تحضير البيئة

```bash
# التأكد من إصدار Java
java -version
# يجب أن يكون 17 أو أحدث

# استنساخ المشروع
git clone <repository-url>
cd EarthQuiz

# منح صلاحيات التنفيذ لـ Gradle Wrapper
chmod +x gradlew
```

### 2. بناء التطبيق

#### بناء سريع (Debug APK)
```bash
# بناء APK للاختبار
./gradlew assembleDebug

# العثور على الملف المبني
ls app/build/outputs/apk/debug/
# سيكون اسم الملف: app-debug.apk
```

#### بناء للإنتاج (Release APK)
```bash
# بناء APK للنشر
./gradlew assembleRelease

# العثور على الملف المبني
ls app/build/outputs/apk/release/
# سيكون اسم الملف: app-release-unsigned.apk
```

### 3. تثبيت التطبيق

#### تثبيت على جهاز متصل
```bash
# تثبيت مباشر على الجهاز
./gradlew installDebug

# أو استخدام ADB
adb install app/build/outputs/apk/debug/app-debug.apk
```

#### تثبيت يدوي
1. انسخ ملف APK إلى الهاتف
2. فعّل "المصادر غير المعروفة" في إعدادات الأمان
3. انقر على ملف APK لتثبيته

## 🛠️ بناء باستخدام Android Studio

### 1. فتح المشروع
1. افتح Android Studio
2. اختر "Open an Existing Project"
3. حدد مجلد المشروع EarthQuiz
4. انتظر حتى تكتمل مزامنة Gradle

### 2. إعداد SDK
1. اذهب إلى **File > Project Structure**
2. تأكد من تحديد **Compile SDK Version: 34**
3. تأكد من تحديد **Build Tools Version: 34.0.0**

### 3. البناء والتشغيل
1. **لاختبار على محاكي**: اضغط على زر "Run" الأخضر
2. **لبناء APK**: اذهب إلى **Build > Build Bundle(s) / APK(s) > Build APK(s)**

## 🔧 إعدادات متقدمة

### تحسين الأداء
```bash
# بناء مع تحسين الذاكرة
./gradlew assembleDebug -Dorg.gradle.jvmargs="-Xmx4g -XX:MaxMetaspaceSize=512m"

# بناء متوازي لتسريع العملية
./gradlew assembleDebug --parallel
```

### تنظيف البناء
```bash
# مسح ملفات البناء السابقة
./gradlew clean

# بناء نظيف جديد
./gradlew clean assembleDebug
```

## 🧪 الاختبار والتحقق

### تشغيل الاختبارات
```bash
# تشغيل اختبارات الوحدة
./gradlew test

# تشغيل اختبارات واجهة المستخدم
./gradlew connectedAndroidTest
```

### فحص الكود
```bash
# فحص جودة الكود
./gradlew lint

# تقرير التبعيات
./gradlew dependencies
```

## 📱 متطلبات الجهاز المستهدف

### الحد الأدنى
- **Android 7.0** (API level 24)
- **1 GB RAM**
- **50 MB** مساحة تخزين

### الموصى به
- **Android 10.0** (API level 29) أو أحدث
- **3 GB RAM** أو أكثر
- **100 MB** مساحة تخزين

## 🔍 استكشاف الأخطاء

### مشاكل شائعة وحلولها

#### خطأ "Java version"
```bash
# التحقق من إصدار Java
java -version

# إذا كان أقل من 17، حدث Java
# لـ Ubuntu/Debian:
sudo apt update
sudo apt install openjdk-17-jdk

# لـ macOS:
brew install openjdk@17
```

#### خطأ "SDK not found"
1. افتح Android Studio
2. اذهب إلى **Tools > SDK Manager**
3. تأكد من تحميل Android 14 (API 34)
4. تأكد من تحميل Build Tools 34.0.0

#### خطأ "Gradle sync failed"
```bash
# حذف وإعادة تحميل Gradle
rm -rf .gradle
./gradlew --refresh-dependencies
```

#### خطأ "Out of memory"
```bash
# زيادة ذاكرة Gradle في gradle.properties
echo "org.gradle.jvmargs=-Xmx4g -XX:MaxMetaspaceSize=512m" >> gradle.properties
```

## 📦 ملفات الإخراج

بعد البناء الناجح، ستجد الملفات في:

```
app/build/outputs/
├── apk/
│   ├── debug/
│   │   └── app-debug.apk         # للاختبار
│   └── release/
│       └── app-release.apk       # للنشر
├── bundle/
│   └── release/
│       └── app-release.aab       # للنشر في Google Play
└── logs/
    └── manifest-merger-debug-report.txt
```

## 🚀 النشر والتوزيع

### تحضير للنشر
1. **توقيع التطبيق**: أنشئ keystore للتوقيع
2. **تحسين الحجم**: فعّل ProGuard/R8
3. **اختبار شامل**: اختبر على أجهزة متعددة
4. **إعداد البيانات الوصفية**: أيقونات ووصف للمتجر

### رفع للمتجر
- **Google Play**: استخدم ملف AAB
- **متاجر أخرى**: استخدم ملف APK موقع

## 📞 الدعم التقني

إذا واجهت مشاكل في البناء:
1. تحقق من ملف `BUILD_ISSUES.md`
2. ابحث في Issues على GitHub
3. أنشئ Issue جديد مع تفاصيل الخطأ

---

**🎯 مع اتباع هذه التعليمات، ستحصل على APK جاهز للتثبيت والاستخدام!**