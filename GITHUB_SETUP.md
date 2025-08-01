# 🚀 رفع EarthQuiz على GitHub

## 📋 خطوات الرفع:

### 1. **📁 تحضير المشروع:**
```bash
# تأكد من أن كل الملفات موجودة
ls -la
```

### 2. **🔧 إنشاء Git Repository:**
```bash
# بداية Git في المشروع
git init

# إضافة ملف .gitignore
echo "*.apk
build/
.gradle/
local.properties
*.iml
.DS_Store" > .gitignore

# إضافة كل الملفات
git add .

# أول commit
git commit -m "🎉 Initial commit: EarthQuiz App - Complete Arabic Quiz App

✨ Features:
- 5 categories with 20+ topics
- Customizable timer (10-60 seconds)
- 20+ questions with explanations
- Material 3 design in Arabic
- Advanced results system
- RTL support

🎯 Ready for deployment!"
```

### 3. **🌐 ربط GitHub Repository:**
```bash
# ربط Repository البعيد
git remote add origin https://github.com/[username]/EarthQuiz.git

# رفع الكود
git branch -M main
git push -u origin main
```

---

## 🎯 معلومات Repository:

### 📝 **اسم المشروع:** `EarthQuiz`
### 📖 **الوصف:** 
```
🧠 EarthQuiz - Advanced Arabic Quiz App

A comprehensive quiz application in Arabic featuring multiple categories, 
customizable timers, and detailed results with modern Material 3 design.

🎮 Features: 5 categories, 20+ questions, timer customization, achievements
📱 Tech: Kotlin, Jetpack Compose, Material 3
🌍 Language: Arabic (RTL support)
```

### 🏷️ **Tags:**
```
android, kotlin, jetpack-compose, material3, arabic, quiz-app, 
education, mobile-app, rtl-support, quiz-game
```

---

## 🔧 ملفات GitHub الإضافية:

### 📄 **.github/ISSUE_TEMPLATE/bug_report.md:**
```markdown
---
name: Bug report
about: Create a report to help us improve
title: ''
labels: bug
assignees: ''
---

**وصف المشكلة**
وصف واضح ومختصر للمشكلة.

**خطوات إعادة الإنتاج**
1. اذهب إلى '...'
2. انقر على '....'
3. اسكرول لأسفل إلى '....'
4. شاهد الخطأ

**السلوك المتوقع**
وصف واضح لما كنت تتوقع حدوثه.

**لقطات الشاشة**
إذا أمكن، أضف لقطات شاشة لمساعدتنا في شرح مشكلتك.

**معلومات الجهاز:**
- النوع: [مثل iPhone6]
- نظام التشغيل: [مثل iOS8.1]
- إصدار التطبيق: [مثل 22]

**سياق إضافي**
أضف أي سياق آخر حول المشكلة هنا.
```

### 📄 **.github/PULL_REQUEST_TEMPLATE.md:**
```markdown
## 📝 وصف التغييرات

وصف مختصر للتغييرات المضافة.

## 🎯 نوع التغيير

- [ ] إصلاح خطأ (تغيير لا يكسر الميزات الموجودة ويحل مشكلة)
- [ ] ميزة جديدة (تغيير يضيف وظيفة جديدة)
- [ ] تغيير مهم (إصلاح أو ميزة تسبب تغيير في الميزات الموجودة)
- [ ] تحديث التوثيق

## 📋 قائمة المراجعة

- [ ] الكود يتبع إرشادات الأسلوب للمشروع
- [ ] راجعت الكود بنفسي
- [ ] أضفت تعليقات للكود، خاصة في المناطق صعبة الفهم
- [ ] أضفت اختبارات تثبت صحة الإصلاح أو الميزة الجديدة
- [ ] الاختبارات الجديدة والموجودة تمر بنجاح محلياً
```

---

## 🎯 GitHub Actions Setup:

### 📄 **.github/workflows/build.yml:** (موجود بالفعل)
✅ سيبني APK تلقائياً عند كل push

### 📄 **.github/workflows/release.yml:**
```yaml
name: 🚀 Create Release

on:
  push:
    tags:
      - 'v*'

jobs:
  release:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v4
    
    - name: Setup JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
        
    - name: Build Release APK
      run: |
        chmod +x gradlew
        ./gradlew assembleRelease
        
    - name: Create Release
      uses: actions/create-release@v1
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
      with:
        tag_name: ${{ github.ref }}
        release_name: EarthQuiz ${{ github.ref }}
        body: |
          🎉 إصدار جديد من تطبيق EarthQuiz!
          
          ## ✨ الميزات:
          - 🧠 5 فئات مختلفة
          - ⏱️ تايمر قابل للتخصيص
          - 📊 نتائج مفصلة
          - 🎨 تصميم عربي عصري
          
          ## 📱 التحميل:
          حمل ملف APK من الأسفل وثبته على جهازك!
        draft: false
        prerelease: false
        
    - name: Upload APK
      uses: actions/upload-release-asset@v1
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
      with:
        upload_url: ${{ steps.create_release.outputs.upload_url }}
        asset_path: app/build/outputs/apk/release/app-release-unsigned.apk
        asset_name: EarthQuiz-${{ github.ref_name }}.apk
        asset_content_type: application/vnd.android.package-archive
```

---

## 📱 كيفية التحميل بعد الرفع:

### للمستخدمين:
1. **اذهب لصفحة المشروع** على GitHub
2. **انقر على "Actions"**
3. **اختر آخر build ناجح**
4. **حمل "EarthQuiz-Debug-APK"**
5. **ثبت على هاتفك!**

### للمطورين:
```bash
git clone https://github.com/[username]/EarthQuiz.git
cd EarthQuiz
./gradlew assembleDebug
```

---

## 🎯 الخطوات التالية:

1. **🔧 رفع الكود:** تشغيل الأوامر أعلاه
2. **⚙️ تفعيل Actions:** GitHub سيبني APK تلقائياً
3. **📤 مشاركة الرابط:** إرسال رابط Repository لك
4. **📱 تحميل APK:** من صفحة Actions
5. **🎉 الاستمتاع:** بالتطبيق الجاهز!

**🚀 بعد الرفع، هبعتلك رابط GitHub Repository مع تعليمات التحميل!**