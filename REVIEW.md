# Repository Review — qa-portfolio

Review date: 2026-08-24
Scope: full repository (`automation/`, `manual/`, `api/`, `performance/`, CI, git hygiene)

## Overview

This is a QA portfolio project: a Selenium/Java/TestNG automation framework testing
`saucedemo.com`, manual test documentation, and stub folders for API (Postman) and
performance (JMeter) testing. As a portfolio piece meant to demonstrate QA skills to
employers, it has good bones (Page Object Model, TestNG `DataProvider`, ExtentReports,
screenshot-on-failure, CI via GitHub Actions) but several issues undercut the
"professional" framing the top-level README claims.

## Findings

### Critical

1. **`README.md` was broken markdown.** *(Fixed in #4.)* Every line was prefixed with a
   literal `#` and escaped punctuation (`\---`, `\##`), rendering unreadably on GitHub —
   the first thing any reviewer sees.

2. **`api/README.md` and `performance/README.md` are empty files**, yet the top-level
   README and `pom.xml` (Appium dependency) advertise API testing, performance testing,
   and implicitly mobile testing. An empty file signals unfinished/abandoned work more
   than no file at all.

3. **Bug reports don't describe actual bugs.** `manual/Bug-Reports.md` #1 and #2 describe
   reasonable/expected behavior as if it were defective (e.g. "error banner appears with
   explicit locked-out text but lacks retry timeout guidance" isn't a bug — SauceDemo
   never promised a retry timer). For a QA portfolio, sample bug reports need to
   demonstrate real defect-finding judgment; as written they read as filler.

4. **`manual/Test-Cases.md` isn't test cases.** It's the same "Test Scenarios" list
   duplicated verbatim from the end of `Test-Plan.md` — one-line scenario titles, no
   preconditions/steps/expected results/test data. This is the artifact most likely to
   be scrutinized for QA rigor, and it currently adds no information beyond the plan.

### Automation code

5. **Broken test**: `CartTest.removeitemTestFormCart()` never adds an item to the cart
   before trying to remove one (each `@BeforeMethod` starts a fresh browser session with
   an empty cart). `cartPage.removeItemFromCart()` will throw `NoSuchElementException`
   since no remove button exists on an empty cart. The first line
   `cartPage.getCartItemCount();` is also a dead statement — its result is discarded.

6. **Duplicate tests**: `LoginTest.testValidLogin()` and `testSuccessfulLogin()` assert
   the same thing via two different code paths (`login()` helper vs. manual field
   calls). This inflates the "coverage" numbers claimed in the README without adding
   value.

7. **`HomePage.java` is an empty stub** — only holds a `driver` field, no
   locators/methods, unused by any test. Dead code.

8. **Locator duplication in `CartPage`**: `addToCartBtn` and `addToCartButton` are the
   same selector (`.btn_inventory`); one is unused, clearly leftover from editing.

9. **No explicit waits anywhere.** Every page object calls `driver.findElement(...)`
   directly with no `WebDriverWait`/`ExpectedConditions`. Works against a stable demo
   site but is a common flakiness source and typically expected in a portfolio meant to
   show Selenium proficiency.

10. **Hardcoded relative paths** in `ExtentReportManager` (`"reports/..."`) and
    `ScreenshotUtils` (`"screenshots/..."`) rather than reading from a config file.

11. **Typos in user-facing report text**: `"QA Protfoloi Report"`, `"Report Resutl "` in
    `ExtentReportManager`; `" Prosduct name should not be null"` in `ProductsTest`.
    Small but visible in generated reports/console output.

12. **Stray Arabic comment** in `ScreenshotUtils`
    (`// إنشاء الفولدر لو مش موجود`) — inconsistent with the rest of the (English)
    codebase.

### Git hygiene

13. **Build/output artifacts are committed**: `automation/target/`,
    `automation/test-output/`, `automation/reports/TestReport.html`, and ~15 screenshot
    PNGs are all tracked in git. The root `.gitignore` excludes `automation/target/`,
    but the nested `automation/.gitignore` uses `/target/` (relative to that directory,
    so effectively the same path) while `test-output/`, `reports/`, and `screenshots/`
    aren't excluded anywhere — so they keep getting re-added on every test run. These
    are regenerated artifacts and shouldn't be version-controlled; they bloat the repo
    and create noisy diffs.

14. **Duplicate/misplaced CI workflow**: `automation/gitHub/workflows/ci.yml` is a
    near-identical copy of `.github/workflows/ci.yml`. GitHub Actions only reads
    workflows from `.github/workflows/`, so this file is dead weight — likely a leftover
    from before commit `c004ab0 "Move CI workflow to correct location"`.

15. **IDE/editor files committed**: `.idea/` and the Eclipse
    `.classpath`/`.project`/`.settings` files are tracked — normally gitignored since
    they're machine/IDE-specific.

## What's solid (no action needed)

- Clean Page Object Model separation (`pages/` vs `tests/` vs `utils/`) with consistent
  constructor-injection of `WebDriver`.
- `BaseTest` correctly wires up `@BeforeMethod`/`@AfterMethod`/`@AfterSuite` for driver
  lifecycle, ExtentReports, and failure screenshots.
- `DataProvider`-driven login test (`testLoginWithMultipleUsers`) is a good pattern.
- The correctly-placed CI workflow itself is simple and correct: checkout, JDK 21 +
  Chrome setup, `mvn clean test`, upload report artifact.
- `Test-Plan.md` is thoughtfully scoped (entry/exit criteria, risks, environment
  matrix, bilingual explanations are a style choice, not a defect).

## Fix Plan

Ordered by impact vs. effort. Each item is independently shippable as its own commit/PR.

| # | Fix | Effort | Status |
|---|-----|--------|--------|
| 1 | Reformat `README.md` | S | ✅ Done (PR #4) |
| 2 | Remove `automation/target/`, `test-output/`, `reports/`, `screenshots/` from git; fix `.gitignore` to cover all of them from repo root | S | Proposed |
| 3 | Delete `automation/gitHub/workflows/ci.yml` (dead duplicate) | S | Proposed |
| 4 | Fix `CartTest.removeitemTestFormCart()` — add an item before removing it; drop the dead `getCartItemCount()` call | S | Proposed |
| 5 | Remove duplicate `LoginTest.testSuccessfulLogin()` (or `testValidLogin()`) — keep one | S | Proposed |
| 6 | Delete empty `HomePage.java`, or implement it if a landing/home flow is actually tested | S | Proposed |
| 7 | Remove the unused `addToCartBtn` duplicate locator in `CartPage` | S | Proposed |
| 8 | Rewrite `manual/Bug-Reports.md` with genuine defects (or clearly relabel current entries as "observations," not bugs) | M | Proposed |
| 9 | Rewrite `manual/Test-Cases.md` as real test cases: preconditions, numbered steps, test data, expected vs. actual, pass/fail | M | Proposed |
| 10 | Populate `api/README.md` with an actual Postman collection + docs, and `performance/README.md` with a JMeter plan + results — or remove those claims from the top-level README until they exist | M | Proposed |
| 11 | Add explicit waits (`WebDriverWait`/`ExpectedConditions`) to page objects instead of bare `findElement` | M | Proposed |
| 12 | Fix typos: "Protfoloi" → "Portfolio", "Resutl" → "Result", "Prosduct" → "Product"; translate/remove the stray Arabic comment for consistency | S | Proposed |
| 13 | Stop tracking `.idea/` and Eclipse project files; add to `.gitignore` | S | Proposed |
| 14 | Externalize hardcoded paths (report/screenshot dirs) into a config file | S | Proposed |

**Suggested sequencing**: do #2–7 and #12–14 together as one "cleanup" PR (all
mechanical, low-risk); do #4–5 as part of that same PR since they touch the same test
files; treat #8–10 as separate content PRs since they involve writing new
documentation/test content rather than fixing existing code.
