function showhidemenu() {
	var mainframeset = parent.document.getElementById('mainFrameset');
	var ctrl = document.getElementById('ctrl');
	var imgsrc=document.getElementById('imgsrc');
	//var menuctrl = window.parent.topframe.document.getElementById('menuctrl');
	//var toolbar = window.parent.topframe.document.getElementById('toolbar');
	if (mainframeset.cols == '0,12,*') {
		imgsrc.src="../ui/images/border_top.jpg";
		mainframeset.cols = '168,12,*';
		ctrl.style.backgroundPosition = '0 center';
		//menuctrl.style.display = 'block';
		//toolbar.style.marginLeft = '';
	} else {
		imgsrc.src="../ui/images/border_top.jpg";
		mainframeset.cols = '0,12,*';
		ctrl.style.backgroundPosition = '-8px center';
		//menuctrl.style.display = 'none';
		//toolbar.style.marginLeft = '8px';
	}
}