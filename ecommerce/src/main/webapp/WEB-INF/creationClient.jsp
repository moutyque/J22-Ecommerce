<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un client</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
    <div>
    <c:import url="/inc/menu.jsp"/>
    </div>
        <div>
            <form method="post" action="clientCreation" enctype='multipart/form-data'>
             <fieldset>
            <c:import url="/WEB-INF/formulaireCreationClient.jsp"/>
             </fieldset>
            <span class="info">${resultat}</span><br />    
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>