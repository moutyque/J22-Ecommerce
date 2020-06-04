<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
   
               
                    <legend>Informations client</legend>
    
                    <label for="nomClient">Nom <span class="requis">*</span></label>
                    <input type="text" id="nomClient" name="nomClient" value="<c:out value="${client.nom}"/>" size="20" maxlength="20" />                    
                    <span class="erreur" ><c:out value="${errors.nomClient}"/></span><br />
                    
                    <label for="prenomClient">Prénom </label>
                    <input type="text" id="prenomClient" name="prenomClient" value="<c:out value="${client.prenom}"/>" size="20" maxlength="20" />
                    <span class="erreur">${errors.prenomClient}</span><br />
    
                    <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
                    <input type="text" id="adresseClient" name="adresseClient" value="<c:out value="${client.adresse}"/>" size="20" maxlength="20" />
                    <span class="erreur">${errors.adresseClient}</span><br />
    
                    <label for="telephoneClient">Numéro de téléphone <span class="requis">*</span></label>
                    <input type="tel" id="telephoneClient" name="telephoneClient" value="<c:out value="${client.telephone}"/>" size="20" maxlength="20" />
                    <span class="erreur">${errors.telephoneClient}</span><br />
                    
                    <label for="emailClient">Adresse email</label>
                    <input type="email" id="emailClient" name="emailClient" value="<c:out value="${client.email}"/>" size="20" maxlength="60" />
                   <span class="erreur">${errors.emailClient}</span><br />
                   
                   <label for="pictureClient">Image<span class="requis">*</span></label>
                    <input type="file" id="image" name="image"  value="<c:out value="${client.image}"/>" />
                	<span class="erreur">${errors.image}</span>
                <br />
                    
</body>
</html>