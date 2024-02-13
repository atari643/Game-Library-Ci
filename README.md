# Game-library with Merge-Request and Continuous Integration

[TP à réaliser en équipe de 3 étudiants]

L'objectif de ce TP est d'utiliser le `workflow`` **GitLab** avec des *Merge-Requests* (*MR)* et des *Issues*, ainsi que la mise en place d'une *Intégration Continue* (*CI*) dans **GitLab**.

A chaque réalisation d'une tâche (correspondant à une *issue*), une *MR* devra être envoyée dans le dépôt.

La mise en place de votre *CI* devra produire un livrable Java et la documentation du code après vous êtes assuré que le code passe les tests.

Tout au long de ce TP, vous n'aurez pas besoin d'échanger entre vous 'oralement' (utiliser les sections `commentaires`  de **GitLab** pour échanger entre vous).

Vous allez améliorer la structuration d'un prototype écrit **Java/Maven** (interface textuelle, pas de stockage pérenne, ... ) pour le **logiciel Ludothèque**.
Le but du code est de gérer les emprunts des jeux par les adhérents dans une ludothèque.

## Organisation

Constituer un groupe de 3 participants noté **P1**, **P2** et **P3**.

- **P1** fork ce dépôt (en mode `private` !) et donne les droits `maintainer` à **P2** et **P3**.

- **P1** invite l'enseignant correcteur de son groupe en tant que `reporter`.

- **P2** et **P3** clonent le dépôt **GitLab** de **P1**.

**Note sur l'origine du fork :**
Afin de pouvoir créer des *MR* à partir d'*Issues* gérées dans votre tableau de bord > **GitLab**, on vous demande également de supprimer la relation avec le projet à l'origine du fork (voir /settings/general/advanced/ Remove fork relationship).

**Note sur le livrable Java :**
La phase *package* de `Maven` package les éléments issus de la compilation dans un format distribuable (JAR, WAR...). La commande Linux `java -jar XXX.jar` exécute le code de l'appli *XXX*.

> **Warning :**
> - Il est interdit de commiter dans la branche `main` directement !
> - Seulement les fichiers sources sont à stocker dans le dépôt git (donc le répertoire `target` du projet `Maven` n'est pas archivé)
> - les *MR* proposés par un membre de l'équipe doivent être validé par un autre membre de l'équipe.
> - Vous devez utiliser l'image Docker *tthor/test*.

## Mise en place de l'intégration continue

**Avant de commencer à travailler sur le code**, vous devez mettre en place l'intégration continue avec **GitLab**.
Pour cela, vous devez ajouter un fichier `.gitlab-ci.yml` à la racine de votre projet.

Ce fichier doit contenir au minimum les étapes suivantes :

- `build` : qui compile le code
- `test` : qui exécute les tests

Au fur et à mesure de l'avancement du projet, vous pourrez ajouter d'autres étapes à votre *CI* :

- `package` : qui crée un livrable Java (JAR)
- `doc` : qui génère la documentation du code dans un *Artifact*
- `check` : qui vérifie la qualité du code avec *Checkstyle*
- des badges pour afficher le statut de la *CI* et de la couverture des tests dans le `README.md` du projet

> **Warning :**
> - Comme pour toute modification de code, vous devez créer une *MR* associée à l'évolution de la *CI* et la faire valider par un autre membre de l'équipe.
> - Vous ferez en sorte que le fichier `.gitlab-ci.yml` soit le plus lisible et générique possible.

## Fonctionnalités à développer

*Chaque membre de l'équipe s'occupe d'une fonctionnalité.*

### Fonctionnalité "Jeu"

Il s'agit de remplacer la dépendance de la classe `LibraryApp` avec la classe  `Game` par une dépendance à l'interface `IGame` :

- Task Game 1 : créer l'interface `IGame`.
- Task Game 2 : dans la classe LibraryApp, remplacer les références à la classe `Game` par une référence à l'interface  `IGame`.

### Fonctionnalité "Adhérent"

Il s'agit de remplacer la dépendance de la classe `LibraryApp` avec la classe `Member` par une dépendance à l'interface `IMember` :

- Task Member 1 : créer l'interface `IMember`.
- Task Member 2 : dans la classe ̀`LibraryApp`, remplacer les références à la classe `Member` par une référence à l'interface  `IMember`.

### Fonctionnalité "Prêt"

Il s'agit d'éliminer la dépendance de la classe `LibraryApp` avec la classe  `Loan` en utilisant la technique d'enveloppement.

- Task Loan 1 : remplacer la méthode `public Loan getLoan()` par la méthode `public String getLoan()` dans `Game` et ...
- Task Loan 2 : modifier le code de la classe `LibraryApp`.
- Task Loan 3 : tester tous les cas d'usage de la méthode `getLoan` de la classe `Game`.
