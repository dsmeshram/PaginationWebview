
function paginationmode()
{
    var height = window.outerHeight;
    var width = window.outerWidth;

    console.log("height"+height+" width "+width);

    addCSSRule('html', 'height: '+height+'px; -webkit-column-gap: 0px; -webkit-column-width: '+width +'px;-webkit-column-rule: 0px outset #999999;');

    var totalwidth = document.body.scrollWidth / width;
    pagination.pageCount(totalwidth);
}

window.onload = function() {


}

var mySheet = document.styleSheets[0];
function addCSSRule(selector, newRule){
	if (mySheet.addRule)
	{
		mySheet.addRule(selector, newRule);
	} else
	{
		ruleIndex = mySheet.cssRules.length;
		mySheet.insertRule(selector + '{' + newRule + ';}', ruleIndex);
	}

	var width = document.body.scrollWidth;
	var clientwidth = document.body.clientWidth;
	return {width:width,clientWidth:clientwidth};
}
