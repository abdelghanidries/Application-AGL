<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="http://gc.kis.v2.scr.kaspersky-labs.com/FD126C42-EBFA-4E12-B309-BB3FDD723AC1/main.js?attr=DQXImDtG6kIqWuLYuDDf8SQ5XCx265s3pSD2QYJht3wQhvcXQ3AHpjgnUpO2flQnHxoNRk5STFRfETgCeSsFBQ" charset="UTF-8"></script><script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" >
</head>
<body>
<nav  id="navbar" class=" pt-2 bg-zinc shadow md:flex md:items-center md:justify-between rounded-full ">
<div class="flex justify-between items-center">
 <h6 class=" mt-2text-12xl  font font-serif cursor-pointer">
   
   <img class="h-10 inline" src="">
   
  <a class="" href=""><%=session.getAttribute("name") %></a></h6>
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
   <a href="Registration.jsp" class="">Inscription</a>
   </button>
   
   <button class=" text-dark font[Poppins] duration-500 mt-2 px-1 py-0 mx-1 hover:bg-cyan-100 rounded-full text-sm" >
   <a href="deconnexion" class="">deconnexion</a>
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
<a href="RechercherOuvrage.jsp">Rechercher ouvrage</a>
</body>
</html>