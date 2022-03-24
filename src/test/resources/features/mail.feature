Feature: mail


  Scenario Outline: register user
    Given I choose my browser <browser>
    Given I enter my mail <mail>
    Given I enter my username <username>
    Given I enter my password <password>
    When I register
    Then I get result <result>
    Examples:
      | browser  | mail                    | username                                                                                                | password          | result     |
      | "chrome" | "testarnisse9000@live.se" | "testarnisse9000"                                                                                         | "m3WwqZt!gPYQ_5h" | "yes"      |
      | "edge"   | "testarnisse5000@live.se" | "testarnisse5kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" | "m3WwqZt!gPYQ_5h" | "long"     |
      | "edge"   | "testarnisse9000@live.se" | "testarnisse9000"                                                                                         | "m3WwqZt!gPYQ_5h" | "existing" |
      | "chrome" | ""                      | "testarnisse0003"                                                                                       | "m3WwqZt!gPYQ_5h" | "missing"  |




