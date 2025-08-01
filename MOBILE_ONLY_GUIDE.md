# 📱 دليل الحصول على APK للتلفون فقط

## 🎯 إذا كان معاك تلفون بس ومعندكش حاسوب:

### ✅ الحلول المتاحة:

#### 1. **🌐 خدمات البناء عبر الإنترنت:**

##### أ) GitHub Actions (مجاني):
1. **ارفع الكود على GitHub**
2. **اعمل Fork للمشروع**
3. **GitHub هيبني APK تلقائياً**
4. **حمل APK من صفحة Releases**

##### ب) Netlify/Vercel:
- خدمات مجانية لبناء التطبيقات
- تدعم Android builds
- تديك رابط مباشر للتحميل

#### 2. **☁️ محررات الكود عبر الإنترنت:**

##### أ) Gitpod:
- محرر كامل في المتصفح
- يدعم Android development
- مجاني لساعات محدودة

##### ب) CodeSandbox:
- تطوير مباشر من المتصفح
- دعم للمشاريع الكبيرة
- إمكانية بناء APK

#### 3. **📲 تطبيقات الأندرويد للتطوير:**

##### أ) Termux:
```bash
# تثبيت Termux من Google Play
# ثم تشغيل الأوامر التالية:
pkg update && pkg upgrade
pkg install openjdk-17 gradle git
git clone <your-repo-url>
cd EarthQuiz
./gradlew assembleDebug
```

##### ب) AIDE (Android IDE):
- IDE كامل للأندرويد
- يدعم مشاريع Gradle
- يمكن بناء APK مباشرة

---

## 🚀 الطريقة الأسهل - GitHub Actions:

### خطوات التفصيل:

#### 1. **رفع الكود على GitHub:**
- إنشاء حساب GitHub (مجاني)
- رفع كل ملفات المشروع
- تفعيل GitHub Actions

#### 2. **إنشاء ملف البناء التلقائي:**
```yaml
# ملف: .github/workflows/build.yml
name: Build APK
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - uses: actions/setup-java@v3
      with:
        java-version: '17'
    - name: Build APK
      run: ./gradlew assembleDebug
    - name: Upload APK
      uses: actions/upload-artifact@v3
      with:
        name: app-debug
        path: app/build/outputs/apk/debug/app-debug.apk
```

#### 3. **تحميل APK:**
- اذهب لصفحة Actions في GitHub
- ابحث عن آخر build ناجح
- حمل ملف APK

---

## 📱 استخدام Termux (الطريقة المباشرة):

### التثبيت:
1. **حمل Termux** من Google Play Store
2. **افتح Termux** وشغل الأوامر:

```bash
# تحديث النظام
pkg update && pkg upgrade -y

# تثبيت Java و Git
pkg install openjdk-17 git -y

# إعطاء صلاحية الوصول للتخزين
termux-setup-storage

# استنساخ المشروع
git clone https://github.com/your-username/EarthQuiz
cd EarthQuiz

# منح صلاحيات
chmod +x gradlew

# بناء APK
./gradlew assembleDebug
```

### العثور على APK:
```bash
# مكان الملف في Termux
ls app/build/outputs/apk/debug/app-debug.apk

# نسخ لمجلد Downloads
cp app/build/outputs/apk/debug/app-debug.apk /storage/emulated/0/Download/
```

---

## 🌟 أسهل طريقة - APK جاهز:

### لو عايز APK جاهز فوراً:
1. **أرسلي رسالة** بإيميلك
2. **هبعتلك APK جاهز** للتحميل المباشر
3. **أو ارفعه على Google Drive** وابعتلك الرابط

---

## 📥 روابط مفيدة:

- **Termux**: https://play.google.com/store/apps/details?id=com.termux
- **AIDE**: https://play.google.com/store/apps/details?id=com.aide.ui
- **GitHub**: https://github.com/
- **Gitpod**: https://gitpod.io/

---

## 💡 نصائح مهمة:

### عند استخدام Termux:
- **شحن التلفون**: العملية تاخد وقت وبطارية
- **اتصال قوي**: لتحميل التبعيات
- **مساحة كافية**: على الأقل 2 GB فارغة

### عند استخدام GitHub:
- **حساب مجاني كافي**
- **البناء يتم في السحابة**
- **مش محتاج موارد من تلفونك**

---

**🎉 بالطرق دي تقدر تحصل على APK بدون ما تحتاج حاسوب!**

أي طريقة تفضل نجربها؟