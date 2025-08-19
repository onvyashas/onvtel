Feature: SIM Card Activation
  As a customer
  I want to activate my SIM card
  So that I can use mobile services

  Scenario: Successful SIM card activation
    Given I activate a SIM card with ICCID "1255789453849037777" and customer email "success@test.com"
    When I query the SIM card record with ID 1
    Then the activation status should be true

  Scenario: Failed SIM card activation
    Given I activate a SIM card with ICCID "8944500102198304826" and customer email "fail@test.com"
    When I query the SIM card record with ID 2
    Then the activation status should be false
