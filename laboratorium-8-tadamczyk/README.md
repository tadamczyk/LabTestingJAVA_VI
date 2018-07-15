# Testowanie aplikacji JAVA 2017-2018
## Laboratorium 8 (Wprowadzenie do BDD - JBehave)

-------------------------------------------

Wszystkie zadania maj¹ byæ zapisane w utworzonym projekcie MAVEN!!!

-------------------------------------------

[x] **Zadanie 1** (1 pkt)

Utwórz projekt MAVEN korzystaj¹c ze szablonu jbehave-quickstart (patrz prezentacja). Nastêpnie skopiuj tutaj 
dowolne zadanie z poprzednich laboratoriów oraz napisz testy akceptacyjne (minimum po 6 testów), które maj¹: 

- Znajdowaæ siê w katalogu **Stories**.
- Maj¹ odpalaæ siê poprzez nazwy klas. 

Testy maj¹ odpalaæ siê przy pomocy polecenia **mvn integration-test** (w przypadku nazw klas). Spójrz jak wygl¹da wynikowy raport z u¿ycia JBehave.

**WSKAZÓWKA:** Patrz przyk³ad pokazany z laboratorium. Zwróæ uwagê na liniê (ona Ÿle generuje siê przez szablon): 

```
ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), new LoadFromClasspath(embeddableClass), parameterConverters, null, new TableTransformers());
```

------------------------------------

**Zadanie 3** (0.25 pkt)

Napisz dwa testy akceptacyjne, które bêd¹ poprzez scenariusze przekazywa³y do metod tak zwane parametry.

-----------------------------

**Zadanie 3** (0.5 pkt)

Dopisz scenariusz, który bêdzie u¿ywa³ preambu³y **@BeforeScenario** oraz **@AfterScenario**. 
Nastêpnie napisz przy pomocy s³owa kluczowego **@AfterScenario** dwie funkcje **fail()** oraz **success()**, które
bêd¹ obs³ugiwaæ ten sam scenariusz testowy, a ponadto: 

- fail() wyœwietli komunikat w konsoli, ¿e test siê nie powiód³.
- success() wyœwietli komunikat o pomyœlnym zakoñczeniu testu.

------------------------------------------

**Zadanie 4** (0.5 pkt) 

Do dowolnego zadania napisz **dwa** scenariusze testowe, które bêd¹ obs³ugiwaæ ten sam kod w jêzyku JAVA, ale
zawartoœæ scenariusza bêdzie nieznacznie siê ró¿ni³a. 

Na przyk³ad dla scenariuszy: 

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

Powinienen obs³ugiwany byæ ten sam kod.