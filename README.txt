# JDBC

## Introduction
- Ce dépôt est un espace pour apprendre à utiliser JDBC de zéro. Il suffit juste de jouer avec le code.
- Il faudra penser à créer les bases de données au préalable (cf script dans le projet pour créer des DB MySQL et PostgreSQL).
- Il faudra créer des utilisateurs pour ces bases de données, puis changer les accès dans le code.
- Dans un premier temps en faisant des requêtes directes avec les DB "core\src\main\java\com\mycompany\tennis\coreTestDeConnection". 
- Par la suite, on apprendra à utiliser JDBC via des implémentations de repositories "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithRepository".
- Finalement, on utilisera des services qui utilise les repositories "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithService".

## Critique
- N'hésitez pas à me faire des retours ou à critiquer ce que j'ai fait, tous les conseils sont bons à prendre !
- L'ensemble du code n'est pas terminé, l'objectif est juste de prendre la main sur JDBC.


# Comparaison DriverManager & DataSource

## DriverManager
- Utilisation simple : DriverManager est généralement plus simple à mettre en œuvre pour les applications simples où une seule source de données est utilisée.
- Pas de pool de connexions intégré : DriverManager ne fournit pas de pool de connexions par défaut. Cela signifie que vous devez gérer manuellement la création et la libération de connexions, ce qui peut entraîner une utilisation inefficace des ressources.
- Bien adapté pour les applications simples et les exemples : En raison de sa simplicité, DriverManager est souvent utilisé dans les exemples et les applications de démonstration.

## DataSource
- Gestion des connexions améliorée : DataSource offre une gestion des connexions améliorée par rapport à DriverManager. Il fournit généralement un pool de connexions, ce qui améliore les performances en réduisant le temps de création et de fermeture des connexions.
- Meilleure prise en charge des transactions : Les DataSources peuvent prendre en charge des transactions distribuées et offrent généralement une meilleure prise en charge des transactions que DriverManager.
- Configurabilité et flexibilité : DataSource offre souvent des fonctionnalités avancées de configuration et de surveillance, ce qui le rend plus adapté aux applications d'entreprise complexes.

## Conclusion
En résumé, si votre application est simple et n'a pas besoin de fonctionnalités avancées telles que la gestion des transactions distribuées ou la surveillance des connexions, vous pouvez utiliser DriverManager. Cependant, pour les applications d'entreprise complexes ou celles nécessitant une gestion avancée des connexions et des transactions, l'utilisation de DataSource est généralement recommandée.


# Comparaison Repository & Service
- Utiliser des services qui interagissent avec des repositories plutôt que d'accéder directement aux repositories depuis les contrôleurs ou d'autres composants de votre application présente plusieurs avantages :

## Séparation des préoccupations (Separation of Concerns - SoC)
- Cette approche permet de séparer clairement la logique métier (contenue dans les services) de la logique d'accès aux données (contenue dans les repositories). Cela rend votre code plus modulaire, plus facile à comprendre et à maintenir.

## Réutilisabilité et modularité
- En encapsulant la logique d'accès aux données dans des repositories et la logique métier dans des services, vous pouvez réutiliser ces services dans différents contextes de votre application sans avoir à réécrire la logique d'accès aux données. Cela favorise la modularité et la réutilisabilité du code.

## Testabilité accrue
- En utilisant des services qui interagissent avec des repositories, vous pouvez facilement mocker les repositories lors des tests unitaires des services. Cela vous permet de tester la logique métier de manière isolée, sans avoir à accéder à la base de données réelle.

## Flexibilité dans le choix des technologies de persistance
En utilisant des services qui dépendent d'interfaces de repository, vous pouvez facilement changer la technologie de persistance (par exemple, passer d'une base de données relationnelle à une base de données NoSQL) sans avoir à modifier la logique métier de votre application. Il vous suffit de fournir une implémentation différente des repositories.

## Sécurité
En centralisant l'accès aux données dans des repositories contrôlés par des services, vous pouvez mettre en œuvre des mécanismes de sécurité plus robustes pour contrôler qui peut accéder à quelles données et avec quelles autorisations.

## En résumé
- L'utilisation de services qui interagissent avec des repositories offre de nombreux avantages en termes de modularité, de réutilisabilité, de testabilité, de flexibilité et de sécurité de votre application. C'est une pratique recommandée dans la conception d'applications robustes et évolutives.
