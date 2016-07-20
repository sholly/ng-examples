# ng-examples

Example code for my Angular 2 conference talk

#requirements

    npm                 // install node 4.4.5 or later
    gulp                // npm install gulp -g
    angular-cli         // npm install angular-cli -g
    typescript          // npm install typescript
    typings             // npm install typings

#01-typescript

Simple typescript compilation demo.  TypeScript (.ts) files are compiled by ts and run by node.

    cd 01-typescript
    tsc                     
    node main
    node fatarrow

# 02-newapp 

Using angular cli demo.  Add a couple components and develop with the cli server.

    cd 02-newapp
    npm install
    ng serve

#03-smart

Integrate an angular 2 app within a Spring MVC app.

    cd 03-smart
    npm install
    ./gradlew tomcatRun

Open http://localhost:8080/