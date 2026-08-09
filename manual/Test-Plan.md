# Test Plan — SauceDemo (Swag Labs)

## 1) Document Control

- Project: SauceDemo (Swag Labs)
- Document: Test Plan
- Version: 0.1
- Author: Ayman Shorafa
- Date: YYYY-MM-DD

## 2) Objective (الهدف)

تحديد استراتيجية اختبار تطبيق SauceDemo لضمان أن تدفقات الشراء الأساسية تعمل بشكل صحيح، وأن التطبيق يتعامل مع الحالات السلبية (Invalid login / Errors) بشكل متوقع، وأن العيوب يتم توثيقها وتتبعها بكفاءة.

## 3) Scope (نطاق الاختبار)

### In Scope

1. Authentication
    - Login (Valid/Invalid)
    - Logout
2. Products
    - عرض قائمة المنتجات
    - Sort (A→Z, Z→A, Price low→high, high→low)
    - Product details
3. Cart
    - Add to cart / Remove
    - Cart badge count
4. Checkout
    - Checkout information form validation
    - Checkout overview
    - Finish / Confirmation
5. Basic UI validations
    - رسائل الخطأ
    - Navigation الأساسية

### Out of Scope (حاليًا)

- Performance testing
- Security testing (باستثناء ملاحظات سطحية)
- Compatibility على أجهزة كثيرة (سنكتفي بمتصفح/متصفحين لاحقًا)

## 4) Test Types (أنواع الاختبارات)

- Smoke Testing
- Functional Testing
- Regression Testing
- Negative Testing
- Exploratory Testing

## 5) Test Environment (بيئة الاختبار)

- URL: https://www.saucedemo.com/
- Browsers: Chrome (أساسي) + (اختياري لاحقًا) Firefox
- Devices: Desktop
- Test Accounts:
    - standard_user / secret_sauce
    - locked_out_user / secret_sauce
    - problem_user / secret_sauce
    - performance_glitch_user / secret_sauce

## 6) Entry Criteria (شروط البدء)

- الموقع متاح بدون أعطال تمنع الاستخدام
- تحديد المتصفحات المطلوبة
- توفر بيانات الدخول (accounts)

## 7) Exit Criteria (شروط الإنهاء)

- تنفيذ جميع حالات الـSmoke
- لا يوجد Bugs من نوع Blocker/Critical مفتوحة
- توثيق نتائج الاختبار + ملخص عيوب

## 8) Risks & Mitigation (المخاطر وتخفيفها)

- تغيّر سلوك الديمو بدون إشعار
    - Mitigation: تثبيت تاريخ الاختبار وتوثيق أي تغير
- Flaky behavior في الديمو (أحيانًا)
    - Mitigation: إعادة المحاولة + توثيق الدليل (screenshots/steps)

## 9) Deliverables (المخرجات)

- Test Plan (هذا الملف)
- Test Scenarios + Test Cases (manual/Test-Cases.md)
- Bug Reports (manual/Bug-Reports.md)
- Test Summary (اختياري لاحقًا)

## 10) Defect Reporting (توثيق العيوب)

- Tool: GitHub Issues (مؤقتًا) أو Jira (إذا حاب)
- محتويات البلاغ:
    - Title
    - Environment (Browser/OS)
    - Preconditions
    - Steps to Reproduce
    - Expected vs Actual
    - Severity / Priority
    - Evidence (Screenshot/Video)

# Test Scenarios — SauceDemo

## Authentication

- TS-AUTH-01: Login with valid credentials (standard_user)
- TS-AUTH-02: Login with invalid username
- TS-AUTH-03: Login with invalid password
- TS-AUTH-04: Login with empty username/password (validation message)
- TS-AUTH-05: Login with locked_out_user (expected error)
- TS-AUTH-06: Logout from application

## Products

- TS-PROD-01: Products page loads successfully after login
- TS-PROD-02: Sort products A→Z
- TS-PROD-03: Sort products by price low→high
- TS-PROD-04: Open product details and validate name/price/image presence

## Cart

- TS-CART-01: Add one item to cart and validate cart badge count
- TS-CART-02: Remove item from cart and validate badge updates
- TS-CART-03: Add multiple items and validate cart list content

## Checkout

- TS-CHK-01: Start checkout and validate required fields
- TS-CHK-02: Complete checkout with valid info and finish order
- TS-CHK-03: Cancel checkout from overview and return to products
