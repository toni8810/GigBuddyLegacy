function showLocationList() {
	var cityList = ['London', 'Birmingham', 'Leeds', 'Glasgow', 'Sheffield', 'Bradford', 'Liverpool', 'Edinburgh', 'Manchester' ,
	                'Bristol', 'Kirklees', 'Fife', 'Wirral', 'North Lanarkshire', 'Wakefield', 'Cardiff', 'Dudley', 'Wigan', 'East Riding' ,
	                'South Lanarkshire', 'Coventry', 'Belfast', 'Leicester', 'Sunderland', 'Sandwell', 'Doncaster', 'Stockport', 'Sefton', 'Nottingham' ,
	                'Newcastle-upon-Tyne', 'Kingston-upon-Hull', 'Bolton', 'Walsall', 'Plymouth', 'Rotherham', 'Stoke-on-Trent', 'Wolverhampton', 'Rhondda, Cynon, Taff', 'South Gloucestershire' ,
	                'Derby', 'Swansea', 'Salford', 'Aberdeenshire', 'Barnsley', 'Tameside', 'Oldham', 'Trafford', 'Aberdeen', 'Southampton' ,
	                'Highland', 'Rochdale', 'Solihull', 'Gateshead', 'Milton Keynes', 'North Tyneside', 'Calderdale', 'Northampton', 'Portsmouth', 'Warrington' ,
	                'North Somerset', 'Bury', 'Luton', 'St Helens', 'Stockton-on-Tees', 'Renfrewshire', 'York', 'Thamesdown', 'Southend-on-Sea', 'New Forest' ,
	                'Caerphilly', 'Carmarthenshire', 'Bath & North East Somerset', 'Wycombe', 'Basildon', 'Bournemouth', 'Peterborough', 'North East Lincolnshire', 'Chelmsford', 'Brighton' ,
		            'South Tyneside', 'Charnwood', 'Aylesbury Vale', 'Colchester', 'Knowsley', 'North Lincolnshire', 'Huntingdonshire', 'Macclesfield', 'Blackpool', 'West Lothian' ,
		            'South Somerset', 'Dundee', 'Basingstoke & Deane', 'Harrogate', 'Dumfries & Galloway', 'Middlesbrough', 'Flintshire', 'Rochester-upon-Medway', 'The Wrekin', 'Newbury' ,
		            'Falkirk', 'Reading', 'Wokingham', 'Windsor & Maidenhead', 'Maidstone', 'Redcar & Cleveland', 'North Ayrshire', 'Blackburn', 'Neath Port Talbot', 'Poole' ,
		            'Wealden', 'Arun', 'Bedford', 'Oxford', 'Lancaster', 'Newport', 'Canterbury', 'Preston', 'Dacorum', 'Cherwell' ,
		            'Perth & Kinross', 'Thurrock', 'Tendring', 'Kings Lynn & West Norfolk', 'St Albans', 'Bridgend', 'South Cambridgeshire', 'Braintree', 'Norwich', 'Thanet' ,
		            'Isle of Wight', 'Mid Sussex', 'South Oxfordshire', 'Guildford', 'Elmbridge', 'Stafford', 'Powys', 'East Hertfordshire', 'Torbay', 'Wrexham Maelor' ,
		            'East Devon', 'East Lindsey', 'Halton', 'Warwick', 'East Ayrshire', 'Newcastle-under-Lyme', 'North Wiltshire', 'South Kesteven', 'Epping Forest', 'Vale of Glamorgan' ,
		            'Reigate & Banstead', 'Chester', 'Mid Bedfordshire', 'Suffolk Coastal', 'Horsham', 'Nuneaton & Bedworth', 'Gwynedd', 'Swale', 'Havant & Waterloo', 'Teignbridge' ,
                    'Cambridge', 'Vale Royal', 'Amber Valley', 'North Hertfordshire', 'South Ayrshire', 'Waverley', 'Broadland', 'Crewe & Nantwich', 'Breckland', 'Ipswich' ,
	                'Pembrokeshire', 'Vale of White Horse', 'Salisbury', 'Gedling', 'Eastleigh', 'Broxtowe', 'Stratford-on-Avon', 'South Bedfordshire', 'Angus', 'East Hampshire' ,		                'East Dunbartonshire', 'Conway', 'Sevenoaks', 'Slough', 'Bracknell Forest', 'West Lancashire', 'West Wiltshire', 'Ashfield', 'Lisburn', 'Scarborough' ,
		            'Stroud', 'Wychavon', 'Waveney', 'Exeter', 'Dover', 'Test Valley', 'Gloucester', 'Erewash', 'Cheltenham', 'Bassetlaw' ,
		            'Scottish Borders'];
	var ul;
	var departureBoxContent = "";
	var locationBox = document.getElementsByName("location")[0];
	//getting ul element
	ul = document.getElementById("list8");
	ul = ul.children[0];
	//capturing what have been typed so far
	var typed = locationBox.value;
	//if something has been typed, capitalize the first character
	if (typed.length > 0) typed = typed.replace(typed.charAt(0),typed.charAt(0).toUpperCase());
	//Emptying the list
	while (ul.firstChild) {
		ul.removeChild(ul.firstChild);
	}
	//Going through all the cities
	for (var i=0; i<cityList.length; i++) {
		if (cityList[i].indexOf(typed) > -1 ) {
			var li = document.createElement("li");
			var a = document.createElement("a");
			a.setAttribute("onclick","writeCityInLocaitonBox(this)");
			var textNode = document.createTextNode(cityList[i]);
			a.appendChild(textNode);
			li.appendChild(a);
			ul.appendChild(li);
		}
	}
		
}
function writeCityInLocaitonBox(element) {
	var city = element.innerHTML;
	var box = document.getElementsByName("location")[0];
	var ul = document.getElementById("list8");
	ul = ul.children[0];
	box.value = city;
	while (ul.firstChild) {
		ul.removeChild(ul.firstChild);
	}
}
function deleteList() {
	var ul = document.getElementById("list8");
	ul = ul.children[0];
	while (ul.firstChild) {
		ul.removeChild(ul.firstChild);
	}
}
function showEventList(box) {
	var typedString = box.value;
	if (typedString.length > 0) typedString = typedString.replace(typedString.charAt(0),typedString.charAt(0).toUpperCase());
	$.ajax({
		type:'POST',
		data: {typed: typedString},
		url: 'createEventList.do',
		success: function(result) {
			$('ul:first').html(result);
		}
	});
		
}
function writeEventIntoEventBox(element) {
	var event = element.innerHTML;
	var box = document.getElementsByName("artist")[0];
	var ul = document.getElementById("list8");
	ul = ul.children[0];
	box.value = event;
	while (ul.firstChild) {
		ul.removeChild(ul.firstChild);
	}
}
function showResult() {
	var list = document.getElementById("list8");
	list.style.display = "none";
	var location = $(':text')[0];
	var event = $(':text')[1];
	var table = $('.table31')[0];
	$.ajax({
		type:'POST',
		data: {locationInput: location.value, eventInput: event.value},
		url: 'getResults.do',
		success: function(result) {
			$('tbody:first').html(result);
		}
	});
	table.style.display = "block";
}
function showRequests(td) {
	var tr = td.parentElement;
	var tds = tr.childNodes;
	var title = tds[0].innerHTML;
	title = title.replace(/&amp;/g, '&');
	var date = tds[2].innerHTML;
	var time = tds[3].innerHTML;
	var location = tds[1].innerHTML;	
	//displaying blue box
	document.getElementById("slideShowPopUpContainer").style.display = "block";
		
	//getting buddy request
	$.ajax({
		type:'POST',
		data: {titleInput: title, dateInput: date, timeInput: time, locationInput: location},
		url: 'getBuddyRequests.do',
		success: function(result) {
			$('.table31:last > table > tbody').html(result);
		}
	});
	document.getElementsByClassName("table31")[1].style.display = "block";
}
function closePopUp(img) {
	var closeThis = img.parentElement;
	closeThis.style.display = "none";
	document.getElementById("applyForm").style.display = "none";
}
function showContactBox(td) {
	document.getElementById("applyForm").style.display = "block";
	var name = td.parentElement.childNodes[1].innerHTML;
	name = name.substring(0,name.indexOf(" "));
	//Your message to (name)
	document.getElementById("personalisedLabel").innerHTML = "Your message to "+name;
	//setting request id
	document.getElementsByName("reqId")[0].setAttribute("value",td.parentElement.id);
}
function showNumOfPeople() {
	document.getElementsByClassName("dropdown")[0].style.display = "inline-block";
	document.getElementsByTagName("td")[1].style.display = "block";
}
function hideNumOfPeople() {
	document.getElementsByClassName("dropdown")[0].style.display = "none";
	document.getElementsByTagName("td")[1].style.display = "none";
}
function characterCounter(textArea) {
	var text = textArea.value;
	document.getElementById("characterCount").innerHTML = "Character Count: "+text.length;
}
function formValidator() {
	//store the request in database
	var radios = document.getElementsByClassName("css-checkbox");
	var radioChecked = false;
	var text = document.getElementsByTagName("textarea")[0].value;
	
	for (var i=0; i<radios.length; i++) {
		if (radios[i].checked == true) radioChecked = true;
	}
	if (radioChecked == false) {
		alert("You have not clicked on any radio button");
		return false;
	}
	if (text.length > 240) {
		alert("You can type a maximum of 240 characters");
		return false;
	}
	setMessageCookie("You have to login to respond to a buddy request");
	return true;
}
function setMessageCookie(message) {
	var cname = "messageCookie";
	var cvalue = message;
	var d = new Date();
	d.setTime(d.getTime() + 10000);
	var expires = "expires="+ d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
	return true;
}