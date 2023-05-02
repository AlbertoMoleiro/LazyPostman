# LazyPostman

Softtek Final Project

## Work Flow
This section will specify how to work with this repository.

### How to clone and work with this repository?
1. Clone this repository
2. Create a new branch with the following name: `feature/<feature-name>` or `bugfix/<bug-name>`
3. Work on your branch
4. Commit your changes
5. Push your changes
6. Create a pull request to the `develop` branch
7. Wait for the code review
8. Merge your pull request

### How to write a commit message?
1. Write the subject starting with the name of the section you are working on, followed by a slash and a space, and then the subject of the commit. `Global/ Add new section`
2. The section could be one of the following:
    - Global
    - Frontend
    - Backend
    - Database
    - Documentation
    - Tests
3. The section should be followed by feature or bugfix when it is necessary. `Frontend/ Feature - Add new button`

### Git commands in order that you will need
1. `git clone <repository-url>`
2. `git checkout -b <branch-name>`
3. `git add .`
4. `git commit -m "<commit-message>"`
5. `git push origin <localBranchName>:<branch-name>`
6. `git checkout develop`
7. `git pull origin develop**`
8. `git checkout <branch-name>`
9. `git merge develop`
10. `git push origin <localBranchName>:<branch-name>`
11. Create a pull request to the `develop` branch
** Develop is the name of the branch that we use to work and merge our changes.
