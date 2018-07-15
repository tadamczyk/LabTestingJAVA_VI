
Scenario: circumfence test
Given I have new circle
When I set radius for circumfence <value1>
Then I get circumfence <resultValue>

Examples:     
|value1|resultValue|
|1|6.28|
|2|12.56|
|3|18.84|

Scenario: poleCircle test
Given I have new circle
When I set radius for poleCircle <value1>
Then I get poleCircle <resultValue>

Examples:     
|value1|resultValue|
|1|3.14|
|2|12.56|
|3|28.26|