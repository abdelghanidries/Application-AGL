
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="" charset="UTF-8"></script><script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" >


</head>
<body >
<div>
<nav  id="navbar" class=" pt-2 bg-zinc shadow md:flex md:items-center md:justify-between rounded-full ">
<div class="flex justify-between items-center">
 <h6 class=" mt-2text-12xl  font font-serif cursor-pointer">
   
   <img class="h-10 inline" src="">
   
   Bibliotheque</h6>
   <span class="text-3xl cursor-pointer md:hidden block">
   <ion-icon name="menu" onclick="Menu(this)"></ion-icon>
   
   
   </span>
 
      
  
    

  </div>
  
  <ul class="md:flex md:items-center z-[-1] md:z-auto md:static absolute  w-full left-0 md:w-auto md:py-0 
   py-4md:pl-0 pl-7 md:opacity-100 opacity-0 top-[-400px] transition-all ease-in duration-500 ">
  <li class="mx-4 my-6 md:my-0 mt-2"><a href="#"  class="text-sm hover:text-cyan-500 duration-500"> Gestionnaire</a></li>
   <li class="mx-4 my-6 md:my-0 mt-2"><a href="#" class="text-sm hover:text-cyan-500 duration-500">Bibliothécaire</a></li>
  <li class="mx-4 my-6 md:my-0 mt-2"><a href="#" class="text-sm hover:text-cyan-500 duration-500"> Abonée</a></li>
  
    
   
   <button class=" text-dark font[Poppins] duration-500 mt-2 px-1 py-0 mx-1 hover:bg-cyan-100 rounded-full text-sm" >
   <a href="connexion.jsp" class="">Connexion</a>
   </button>
  
  </ul>
</nav>
</div>
<script>function Menu(e){
	let list = document.querySelector('ul');
	
	e.name === 'menu' ? (e.name = "close", list.classList.add('top-[50px]'),list.classList.add('opacity-100')) :
		(e.name = "menu",list.classList.remove('top-[50px]'),list.classList.remove('opacity-100'))
}






</script>
<div>
<!-- slider -->
<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="images/bib1.jpg" class="d-block w-100 vh-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5 class="pb-4">Bibliothèque d'universitée abderhemane mira Bejaia</h5>
        <p >Explorer, Apprendre, Évoluer : Votre Connexion Savante</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="images/bib2.jpg" class="d-block w-100 vh-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Second slide label</h5>
        <p>Some representative placeholder content for the second slide.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="images/bib3.jpg" class="d-block w-100 vh-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h5>Third slide label</h5>
        <p>Some representative placeholder content for the third slide.</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </button>
</div>
</div>
   <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" ></script>


</body>
</html>