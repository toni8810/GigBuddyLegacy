<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<form method="post" action="/members/logout.do" id="logoutForm">
		<input type="submit" value="log out" />
		<p>Welcome <span>${param.username}</span></p>
	</form>
	<form action="/members/showMessages.do" id="messageBox">
		<c:if test="${numOfUnreadMessages > 0}" >
			<div id="numOfMessage">${numOfUnreadMessages}</div>
		</c:if>
		<input type="image" src="/images/letter.png" alt="letter"/>
	</form>
</div>
<div id="navigationBarHolder">
	<ol id="navigationBar">
		<li><a id="firstItem" href="/members/index.jsp">Home</a></li>
		<li><a class="menuItem" href="/members/events.jsp">Events</a></li>
		<li><a class="menuItem" href="/members/contactUs.jsp">Contact Us</a></li>
		<li><a class="menuItem" href="/members/profile.jsp">Edit Profile</a></li>
		<li><a class="menuItem" href="/members/findMembers.jsp">Find Members</a></li>
		<li><a class="menuItem" href="/members/manage.jsp">Manage Requests/Events</a></li>
	</ol>
	<img src="${sessionScope.mainImage}" alt="logo" id="logo" />
</div>
<div id='cssmenu'>
<ul>
   <li><a href='/members/index.jsp'><span>Home</span></a></li>
   <li><a href='/members/events.jsp'><span>Events</span></a></li>
   <li><a href='/members/contactUs.jsp'><span>Contact Us</span></a></li>
   <li><a href='/members/profile.jsp'><span>Edit Profile</span></a></li>
   <li><a href='/members/findMembers.jsp'><span>Find Members</span></a></li>
   <li class='last'><a href='/members/manage.jsp'><span>Manage Requests/Events</span></a></li>
</ul>
</div>