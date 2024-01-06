<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="css/main.css" />

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">



</head>
<body>
<nav  id="navbar" class=" pt-2 bg-zinc md:flex md:items-center md:justify-between rounded-full ">
<div class="flex justify-between items-center">
 <h6 class=" md:my-3 mt-2text-12xl  font font-serif cursor-pointer">
   
   <img class="h-10 inline" src="">
   
   Bibliotheque</h6>
   <span class="text-3xl cursor-pointer md:hidden block">
   <ion-icon name="menu" onclick="Menu(this)"></ion-icon>
   
   
   </span>
 
      
  
    

  </div>
  
  <ul class="md:flex md:items-center z-[-1] md:z-auto md:static absolute  w-full left-0 md:w-auto md:py-0 
   py-4md:pl-0 pl-7 md:opacity-100 opacity-0 top-[-400px] transition-all ease-in duration-500 ">
    <li class="mx-4 my-6 md:my-3 mt-2"><a href="home.jsp"  class="text-sm hover:text-cyan-500 duration-500">Acceuil</a></li>
  <li class="mx-4 my-6 md:my-3 mt-2"><a href="#"  class="text-sm hover:text-cyan-500 duration-500"> Gestionnaire</a></li>
   <li class="mx-4 my-6 md:my-3 mt-2"><a href="#" class="text-sm hover:text-cyan-500 duration-500">Bibliothécaire</a></li>
  <li class="mx-4 my-6 md:my-3 mt-2"><a href="#" class="text-sm hover:text-cyan-500 duration-500"> Abonée</a></li>
  
   
   
   
  
  </ul>
</nav>

<script>function Menu(e){
	let list = document.querySelector('ul');
	
	e.name === 'menu' ? (e.name = "close", list.classList.add('top-[50px]'),list.classList.add('opacity-100')) :
		(e.name = "menu",list.classList.remove('top-[50px]'),list.classList.remove('opacity-100'))
}






</script>
<form class="container pt-5" method="post" action="said" novalidate>
        <div class="form-group row">
            <label for="matricule" class="col-sm-2 col-form-label">Matricule</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="matricule" name="matricule" required>
                <div class="invalid-feedback">
                    Veuillez saisir votre matricule.
                </div>
            </div>
        </div>
        <div class="form-group row">
            <label for="mdp" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="mdp" name="mdp" required>
                <div class="invalid-feedback">
                    Veuillez saisir votre mot de passe.
                </div>
            </div>
        </div>
        <button class="btn btn-primary " type="submit" style="color: green;">Connexion</button>
    </form>

<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();

</script>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>



</body>
   
     
   
    
      

</html>