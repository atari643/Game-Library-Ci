# Game-library with Merge-Request and Continuous Integration

TP en équipe de 3 étudiants

L'objectif de ce TP est d'utiliser le `workflow`` **GitLab** avec des *Merge-Requests* (*MR)* et des *Issues*, ainsi que la mise en place d'une *Intégration Continue* (*CI*) dans **GitLab**.

Vous allez améliorer la structuration d'un code succint sur la gestion d'une *ludothèque*. Ce code est écrit en **Java/Maven** . 
A chaque réalisation d'une tâche (correspondant à un *issue*), une *MR* devra être envoyée dans le dépôt.

La mise en place de votre *CI* devra produire une archive du code et la documentation du code après vous êtes assuré que le code passe les tests.

Tout au long de ce TP, vous n'aurez pas besoin d'échanger entre vous 'oralement' (utiliser les sections `commentaires`  de **GitLab** pour échanger entre vous). 

## Organisation

Constituer un groupe de 3 participants noté **P1**, **P2** et **P3**.


**P1** fork ce dépôt (en mode `private` !) et donne les droits `maintainer` à **P2** et **P3**.

**P1** invite l'enseignant correcteur de son groupe en tant que `reporter`.


**Note**
Afin de pouvoir créer des *MR* à partir d'*Issues* gérées dans votre tableau de bord **GitLab**, on vous demande également de supprimer la relation avec le projet à l'origine du fork (voir /settings/general/advanced/ Remove fork relationship).

**P2** et **P3** clonent le dépôt **GitLab** de **P1**.

**Warning**
- Il est interdit de commiter dans la branche `main` directement !
- Vous devez utiliser l'image Docker *tthor/test*.
- les *MR* proposé par un member de l'équipe doivent être validé par un autre membre de l'équipe.

Chaque membre de l'équipe s'occupe d'une fonctionnalité.

## fonctionnalité Jeu
Il s'agit de remplacer la dépendance de la classe `LibraryApp` avec la classe  `Game` par une dépendance à l'interface `IGame` :
- Task Game 1 : créer l'interface `IGame`.
- Task Game 2 : dans la classe LibraryApp remplacer les références à la classe `Game` par une référence à l'interface  `IGame`.

## fonctionnalité adhérent
Il s'agit de remplacer la dépendance de la classe `LibraryApp` avec la classe  `Member` par une dépendance à l'interface `IMember` :
- Task Member 1 : créer l'interface `IMember`.
- Task Member 2 : dans la classe ̀`LibraryApp` remplacer les références à la classe `Member` par une référence à l'interface  `IMember`.

## fonctionnalité prêt
Il s'agit d'éliminer la dépendance de la classe `LibraryApp` avec la classe  `Loan` en utilisant la technique d'enveloppement.
- Task Loan 1 : remplacer la méthode `public Loan getLoan()` par la méthode `public String getLoan()` dans `Game` et ...
- Task Loan 2 : modifier le code de la classe `LibraryApp`.
- Task Loan 3 : tester tous les cas d'usage de la méthode `getLoan` de la classe `Game`.
