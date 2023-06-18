### Java Global MP 2023. Module #2: Java 8, 9, 10, 11+

Java and Maven versions used:
```
java 17.0.5 2022-10-18 LTS
Apache Maven 3.8.7
```

There is a Demo class created to demonstrate usage of created modules and classes.\
To run the application: 
```
> mvn install
> java --module-path .\mods\ -m application/org.jmp.bank.Demo
```

<details>
  <summary>Output example</summary>

```
==================================
Create bank card demonstration
==================================
Found 1 IBank services configured.

New bank cards created:
BankCard{number='9586-9936-7827-9988', user=User{name='Test', surname='Testovich', birthday=1999-12-31}', type=CREDIT}
BankCard{number='9486-4316-8314-1712', user=User{name='Maksim', surname='Marchuk', birthday=1989-09-30}', type=DEBIT}

All bank cards:
BankCard{number='1111-2222-3333-4444', user=User{name='John', surname='Doe', birthday=1980-01-05}', type=CREDIT}
BankCard{number='1234-1234-1234-1234', user=User{name='Johanna', surname='Doe', birthday=1990-02-25}', type=CREDIT}
BankCard{number='2222-2222-2222-2222', user=User{name='Vasiliy', surname='Pupkin', birthday=2022-03-15}', type=DEBIT}
BankCard{number='3333-3333-3333-3333', user=User{name='Ivan', surname='Petrov', birthday=1980-08-10}', type=DEBIT}
BankCard{number='4321-4321-4321-4321', user=User{name='Kate', surname='Perry', birthday=1989-08-20}', type=CREDIT}
BankCard{number='9586-9936-7827-9988', user=User{name='Test', surname='Testovich', birthday=1999-12-31}', type=CREDIT}
BankCard{number='9486-4316-8314-1712', user=User{name='Maksim', surname='Marchuk', birthday=1989-09-30}', type=DEBIT}

==================================
Subscribe and get subscription demonstration
==================================
Found 1 IBankService services configured.

New subscriptions have been created for card numbers:
9586-9936-7827-9988
9486-4316-8314-1712

All subscriptions:
Subscription{bankCard='1111-2222-3333-4444', startDate=2005-10-12}
Subscription{bankCard='1234-1234-1234-1234', startDate=2007-01-01}
Subscription{bankCard='2222-2222-2222-2222', startDate=2012-12-19}
Subscription{bankCard='3333-3333-3333-3333', startDate=2022-01-15}
Subscription{bankCard='4321-4321-4321-4321', startDate=2020-03-16}
Subscription{bankCard='9586-9936-7827-9988', startDate=2023-01-17}
Subscription{bankCard='9486-4316-8314-1712', startDate=2023-01-17}

==================================
Get average user age demonstration
==================================
Average age: 30.0

==================================
Is payable user demonstration
==================================
User{name='John', surname='Doe', birthday=1980-01-05}
Is payable: true
User{name='Johanna', surname='Doe', birthday=1990-02-25}
Is payable: true
User{name='Vasiliy', surname='Pupkin', birthday=2022-03-15}
Is payable: false
User{name='Ivan', surname='Petrov', birthday=1980-08-10}
Is payable: true
User{name='Kate', surname='Perry', birthday=1989-08-20}
Is payable: true

==================================
Get subscription exception demonstration
==================================
Exception caught:
org.jmp.bank.cloud.service.impl.BankCardNotFoundException: Bank card not found
        at java.base/java.util.Optional.orElseThrow(Optional.java:403)
        at jmp.cloud.service.impl@1.0-SNAPSHOT/org.jmp.bank.cloud.service.impl.BankService.getSubscriptionByBankCardNumber(BankService.java:26)
        at application@1.0-SNAPSHOT/org.jmp.bank.Demo.main(Demo.java:57)

==================================
Get subscription by condition demonstration
==================================
Subscriptions which are start after 2020-01-01
Subscription{bankCard='3333-3333-3333-3333', startDate=2022-01-15}
Subscription{bankCard='4321-4321-4321-4321', startDate=2020-03-16}
Subscription{bankCard='9586-9936-7827-9988', startDate=2023-01-17}
Subscription{bankCard='9486-4316-8314-1712', startDate=2023-01-17}

Attempt to add to unmodifiable list
Exception caught:
java.lang.UnsupportedOperationException
        at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
        at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
        at application@1.0-SNAPSHOT/org.jmp.bank.Demo.main(Demo.java:72)

Attempt to add to unsupported null to unmodifiable list
Exception caught:
java.lang.UnsupportedOperationException
        at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:142)
        at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:147)
        at application@1.0-SNAPSHOT/org.jmp.bank.Demo.main(Demo.java:80)
```
</details>