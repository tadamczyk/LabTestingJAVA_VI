# Testowanie aplikacji JAVA 2017-2018
## Laboratorium 8 (Wprowadzenie do BDD - JBehave)

-------------------------------------------

Wszystkie zadania maj� by� zapisane w utworzonym projekcie MAVEN!!!

-------------------------------------------

[x] **Zadanie 1** (1 pkt)

Utw�rz projekt MAVEN korzystaj�c ze szablonu jbehave-quickstart (patrz prezentacja). Nast�pnie skopiuj tutaj 
dowolne zadanie z poprzednich laboratori�w oraz napisz testy akceptacyjne (minimum po 6 test�w), kt�re maj�: 

- Znajdowa� si� w katalogu **Stories**.
- Maj� odpala� si� poprzez nazwy klas. 

Testy maj� odpala� si� przy pomocy polecenia **mvn integration-test** (w przypadku nazw klas). Sp�jrz jak wygl�da wynikowy raport z u�ycia JBehave.

**WSKAZ�WKA:** Patrz przyk�ad pokazany z laboratorium. Zwr�� uwag� na lini� (ona �le generuje si� przez szablon): 

```
ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters, null, new TableTransformers());
```

------------------------------------

**Zadanie 3** (0.25 pkt)

Napisz dwa testy akceptacyjne, kt�re b�d� poprzez scenariusze przekazywa�y do metod tak zwane parametry.

-----------------------------

**Zadanie 3** (0.5 pkt)

Dopisz scenariusz, kt�ry b�dzie u�ywa� preambu�y **@BeforeScenario** oraz **@AfterScenario**. 
Nast�pnie napisz przy pomocy s�owa kluczowego **@AfterScenario** dwie funkcje **fail()** oraz **success()**, kt�re
b�d� obs�ugiwa� ten sam scenariusz testowy, a ponadto: 

- fail() wy�wietli komunikat w konsoli, �e test si� nie powi�d�.
- success() wy�wietli komunikat o pomy�lnym zako�czeniu testu.

------------------------------------------

**Zadanie 4** (0.5 pkt) 

Do dowolnego zadania napisz **dwa** scenariusze testowe, kt�re b�d� obs�ugiwa� ten sam kod w j�zyku JAVA, ale
zawarto�� scenariusza b�dzie nieznacznie si� r�ni�a. 

Na przyk�ad dla scenariuszy: 

```
Scenario: First Scenario
Given a value x by 5
Given a value y by 10
Then sum of value x and y is 15

Scenario: Second Scenario
Given a value x by 5
Given a value y by 10
Then sum of value x and y becomes 15
```

Powinienen obs�ugiwany by� ten sam kod.