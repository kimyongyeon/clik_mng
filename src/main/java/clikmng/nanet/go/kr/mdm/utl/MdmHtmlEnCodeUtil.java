package clikmng.nanet.go.kr.mdm.utl;

public class MdmHtmlEnCodeUtil {

	public String HtmlSpecialCharControl(String ctlTxt) throws Exception
	{
		try
		{
			ctlTxt = ctlTxt.replaceAll("[!][#][A-Z]([0-9])*[#]*" , "");
			ctlTxt = ctlTxt.replaceAll("[#][!]","");
			ctlTxt = ctlTxt.replaceAll("[!][rR]" , "");
			ctlTxt = ctlTxt.replaceAll("[rR][!]" , "");
			ctlTxt = ctlTxt.replaceAll("[!][bB]" , "");
			ctlTxt = ctlTxt.replaceAll("[bB][!]" , "");
			ctlTxt = ctlTxt.replaceAll("[!][A-Za-z]" , "");	
			ctlTxt = ctlTxt.replaceAll("[A-Za-z][!]" , "");
			ctlTxt = ctlTxt.replaceAll("[|][|]" , "");

			ctlTxt = ctlTxt.replaceAll("(([#])*nbsp[&]nbsp[;])|(([#])*nbsp[;])" , "&nbsp;");
			ctlTxt = ctlTxt.replaceAll("&nbsp;&nbsp;","&nbsp;");
			ctlTxt = ctlTxt.replaceAll("([#])*amp[;]" , "&amp;");
			ctlTxt = ctlTxt.replaceAll("([#])*lt[;]" , "&lt;");
			ctlTxt = ctlTxt.replaceAll("([#])*gt[;]" , "&gt;");
			ctlTxt = ctlTxt.replaceAll("([#])*quot[;]" , "&quot;");
			ctlTxt = ctlTxt.replaceAll("([#])*frasl[;]" , "&frasl;");
			ctlTxt = ctlTxt.replaceAll("([#])*euro[;]" , "&euro;");
			ctlTxt = ctlTxt.replaceAll("([#])*dagger[;]" , "&dagger;");
			ctlTxt = ctlTxt.replaceAll("([#])*Dagger[;]" , "&Dagger;");
			ctlTxt = ctlTxt.replaceAll("([#])*permil[;]" , "&permil;");
			ctlTxt = ctlTxt.replaceAll("([#])*lsquo[;]" , "&lsquo;");
			ctlTxt = ctlTxt.replaceAll("([#])*rsquo[;]" , "&rsquo;");
			ctlTxt = ctlTxt.replaceAll("([#])*ldquo[;]" , "&ldquo;");
			ctlTxt = ctlTxt.replaceAll("([#])*rdquo[;]" , "&rdquo;");
			ctlTxt = ctlTxt.replaceAll("([#])*bull[;]" , "&bull;");
			ctlTxt = ctlTxt.replaceAll("([#])*ndash[;]" , "&ndash;");
			ctlTxt = ctlTxt.replaceAll("([#])*mdash[;]" , "&mdash;");
			ctlTxt = ctlTxt.replaceAll("([#])*iexcl[;]" , "&iexcl;");
			ctlTxt = ctlTxt.replaceAll("([#])*cent[;]" , "&cent;");
			ctlTxt = ctlTxt.replaceAll("([#])*pound[;]" , "&pound;");
			ctlTxt = ctlTxt.replaceAll("([#])*curren[;]" , "&curren;");
			ctlTxt = ctlTxt.replaceAll("([#])*yen[;]" , "&yen;");
			ctlTxt = ctlTxt.replaceAll("([#])*brvbar[;]" , "&brvbar;");
			ctlTxt = ctlTxt.replaceAll("([#])*brkbar[;]" , "&brkbar;");
			ctlTxt = ctlTxt.replaceAll("([#])*sect[;]" , "&sect;");
			ctlTxt = ctlTxt.replaceAll("([#])*uml[;]" , "&uml;");
			ctlTxt = ctlTxt.replaceAll("([#])*die[;]" , "&die;");
			ctlTxt = ctlTxt.replaceAll("([#])*copy[;]" , "&copy;");
			ctlTxt = ctlTxt.replaceAll("([#])*ordf[;]" , "&ordf;");
			ctlTxt = ctlTxt.replaceAll("([#])*laquo[;]" , "&laquo;");
			ctlTxt = ctlTxt.replaceAll("([#])*not[;]" , "&not;");
			ctlTxt = ctlTxt.replaceAll("([#])*shy[;]" , "&shy;");
			ctlTxt = ctlTxt.replaceAll("([#])*reg[;]" , "&reg;");
			ctlTxt = ctlTxt.replaceAll("([#])*macr[;]" , "&macr;");
			ctlTxt = ctlTxt.replaceAll("([#])*hibar[;]" , "&hibar;");
			ctlTxt = ctlTxt.replaceAll("([#])*deg[;]" , "&deg;");
			ctlTxt = ctlTxt.replaceAll("([#])*plusmn[;]" , "&plusmn;");
			ctlTxt = ctlTxt.replaceAll("([#])*sup2[;]" , "&sup2;");
			ctlTxt = ctlTxt.replaceAll("([#])*sup3[;]" , "&sup3;");
			ctlTxt = ctlTxt.replaceAll("([#])*acute[;]" , "&acute;");
			ctlTxt = ctlTxt.replaceAll("([#])*micro[;]" , "&micro;");
			ctlTxt = ctlTxt.replaceAll("([#])*para[;]" , "&para;");
			ctlTxt = ctlTxt.replaceAll("([#])*middot[;]" , "&middot;");
			ctlTxt = ctlTxt.replaceAll("([#])*cedil[;]" , "&cedil;");
			ctlTxt = ctlTxt.replaceAll("([#])*sup1[;]" , "&sup1;");
			ctlTxt = ctlTxt.replaceAll("([#])*ordm[;]" , "&ordm;");
			ctlTxt = ctlTxt.replaceAll("([#])*raquo[;]" , "&raquo;");
			ctlTxt = ctlTxt.replaceAll("([#])*frac14[;]" , "&frac14;");
			ctlTxt = ctlTxt.replaceAll("([#])*frac12[;]" , "&frac12;");
			ctlTxt = ctlTxt.replaceAll("([#])*frac34[;]" , "&frac34;");
			ctlTxt = ctlTxt.replaceAll("([#])*iquest[;]" , "&iquest;");
			ctlTxt = ctlTxt.replaceAll("([#])*divide[;]" , "&divide;");
			
			ctlTxt = ctlTxt.replaceAll("([#])*([0-9])*[;]" , "&nbsp;");
			
			ctlTxt = ctlTxt.replaceAll("\\\\" , "");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ctlTxt;
	}
}
