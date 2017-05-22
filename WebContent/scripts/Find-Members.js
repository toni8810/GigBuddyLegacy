var allImagesGlobal = [];
function setAllImagesGlobal(img) {
	allImagesGlobal = [];
	var container = document.getElementById("slideShowPopUpContainer");
	container.style.display = "block";
	var id = img.parentElement.parentElement.id;
	$.ajax({
		type:'POST',
		data: {idInput: id},
		url: '/getUserPics.do',
		success: function(result) {
			$.each(result, function(index, item) { // Iterate over the JSON array.
	            allImagesGlobal[index] = "/getImage.do?fileName="+item;
	     });			}
	});
	//load images into hidden tags
	$(document).ajaxStop(function () {
		var ol = document.getElementsByClassName("hidden")[0];
		var li;
		var imgTag;
		for (var i=0; i<allImagesGlobal.length; i++) {
			li = document.createElement("li");
			imgTag = document.createElement("img");
			imgTag.setAttribute("src", allImagesGlobal[i]);
			li.appendChild(imgTag);
			ol.appendChild(li);
		}
  	});
	
	showImages(img.src);
}
function getResults() {
	var name = document.getElementsByName("name")[0].value;
	$.ajax({
		type:'POST',
		data: {nameInput: name},
		url: '/getMembersResults.do',
		success: function(result) {
			$('tbody:first').html(result);
		}
	});
	document.getElementsByClassName("table31")[0].style.display = "block";
}
function showImages(imgSrc) {
	var imgDynamic = document.getElementById("dynamicImage");
	imgDynamic.src = imgSrc;
}
function closePopUp(img) {
	var closeThis = img.parentElement;
	closeThis.style.display = "none";
}
function showNextImage() {
	var currentImage = document.getElementById("dynamicImage").src;
	currentImage = currentImage.substring(19);
	for (var i=0; i<allImagesGlobal.length; i++) {
		if (allImagesGlobal[i] == currentImage) {
			if (i+1 < allImagesGlobal.length)  {
				showImages(allImagesGlobal[i+1]);
			}
			else showImages(allImagesGlobal[0]);
		}
	}
}
function showPreviousImage() {
	var currentImage = document.getElementById("dynamicImage").src;
	currentImage = currentImage.substring(19);
	for (var i=0; i<allImagesGlobal.length; i++) {
		if (allImagesGlobal[i] == currentImage) {
			if (i-1 > -1) showImages(allImagesGlobal[i-1]);
			else showImages(allImagesGlobal[allImagesGlobal.length-1]);
		}
	}
}