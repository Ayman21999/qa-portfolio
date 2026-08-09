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
