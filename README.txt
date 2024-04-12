### JDBC

## Introduction
Ce dépôt est un espace pour apprendre à utiliser JDBC de zéro. Il suffit juste de jouer avec le code.
- Il faudra penser à créer les bases de données au préalable (cf script dans le projet pour créer des DB MySQL et PostgreSQL).
- Il faudra créer des utilisateurs pour ces bases de données, puis changer les accès dans le code.
Dans un premier temps en faisant des requêtes directes avec les DB "core\src\main\java\com\mycompany\tennis\coreTestDeConnection". 
Par la suite, on apprendra à utiliser JDBC via des implémentations de repository "core\src\main\java\com\mycompany\tennis\TestDeConnectionWithRepository".

## Critique
N'hésitez pas à me faire des retours ou à critiquer ce que j'ai fait, tout conseil est bon à prendre !

### Comparaison DriverManager & DataSource

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
