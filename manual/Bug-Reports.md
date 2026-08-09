# Bug Reports — SauceDemo

## Bug Report #001: Locked-out user error message display issue
- **Bug ID:** BUG-AUTH-001
- **Severity:** High | **Priority:** High
- **Environment:** Windows 11 / Chrome (Latest)
- **URL:** `https://www.saucedemo.com/`

### Steps to Reproduce:
1. Go to SauceDemo login page.
2. Enter `locked_out_user` as username and `secret_sauce` as password.
3. Click "Login".

### Expected Result:
Error message should clearly state user account status with standard UI styling.
### Actual Result:
Error banner appears with explicit locked-out text but lacks retry timeout guidance.

---

## Bug Report #002: Sorting products by price does not persist on page refresh
- **Bug ID:** BUG-PROD-001
- **Severity:** Medium | **Priority:** Medium
- **Environment:** Windows 11 / Chrome (Latest)
- **URL:** `https://www.saucedemo.com/inventory.html`

### Steps to Reproduce:
1. Log in as `standard_user`.
2. Change sort filter to "Price (low to high)".
3. Refresh the page (F5).

### Expected Result:
Selected sort option should persist after refresh.
### Actual Result:
Sort dropdown resets to default "Name (A to Z)".

---

## Bug Report #003: Checkout process allows submission with empty fields
- **Bug ID:** BUG-CHK-001
- **Severity:** High | **Priority:** High
- **Environment:** Windows 11 / Chrome (Latest)
- **URL:** `https://www.saucedemo.com/checkout-step-one.html`

### Steps to Reproduce:
1. Add any product to cart and click "Checkout".
2. Leave First Name, Last Name, and Postal Code empty.
3. Click "Continue".

### Expected Result:
Form validation triggers displaying required field errors.
### Actual Result:
Order advances without validation.