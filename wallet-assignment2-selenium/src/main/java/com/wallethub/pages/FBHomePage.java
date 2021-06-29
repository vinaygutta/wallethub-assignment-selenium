package com.wallethub.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.wallethub.BaseTest;

public class FBHomePage extends BaseTest {

	@FindBy(css = ".k4urcfbm.qv66sw1b .l9j0dhe7")
	private WebElement postLink;
	
	@FindBy(css = "#mount_0_0_E3 > div > div:nth-child(1) > div > div.rq0escxv.l9j0dhe7.du4w35lb > div > div > div.j83agx80.cbu4d94t.d6urw2fd.dp1hu0rb.l9j0dhe7.du4w35lb > div.rq0escxv.l9j0dhe7.du4w35lb.j83agx80.pfnyh3mw.taijpn5t.gs1a9yip.owycx6da.btwxx1t3.dp1hu0rb.p01isnhg > div > div.rq0escxv.l9j0dhe7.du4w35lb.j83agx80.g5gj957u.pmt1y7k9.buofh1pr.hpfvmrgz.taijpn5t.gs1a9yip.owycx6da.btwxx1t3.f7vcsfb0.fjf4s8hc.b6rwyo50.oyrvap6t > div > div > div:nth-child(3) > div > div.pedkr2u6.tn0ko95a.pnx7fd3z > div > div:nth-child(2) > div > div > div > div > div > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(4) > div > div > div.cwj9ozl2.tvmbv18p > div.ecm0bbzt.hv4rvrfc.e5nlhep0.dati1w0a.j83agx80.btwxx1t3.lzcic4wl > div.rj1gh0hx.buofh1pr.ni8dbmo4.stjgntxs.rz4wbd8a > div.rs4xwbwe.q4pta889 > div")
	private WebElement entertopostText;
	
	@FindBy(css = "#mount_0_0_E3 > div > div:nth-child(1) > div > div.rq0escxv.l9j0dhe7.du4w35lb > div > div > div.j83agx80.cbu4d94t.d6urw2fd.dp1hu0rb.l9j0dhe7.du4w35lb > div.rq0escxv.l9j0dhe7.du4w35lb.j83agx80.pfnyh3mw.taijpn5t.gs1a9yip.owycx6da.btwxx1t3.dp1hu0rb.p01isnhg > div > div.rq0escxv.l9j0dhe7.du4w35lb.j83agx80.g5gj957u.pmt1y7k9.buofh1pr.hpfvmrgz.taijpn5t.gs1a9yip.owycx6da.btwxx1t3.f7vcsfb0.fjf4s8hc.b6rwyo50.oyrvap6t > div > div > div:nth-child(3) > div > div.pedkr2u6.tn0ko95a.pnx7fd3z > div > div:nth-child(2) > div > div > div > div > div > div > div > div > div > div > div:nth-child(2) > div > div:nth-child(4) > div > div > div.cwj9ozl2.tvmbv18p > div.ecm0bbzt.hv4rvrfc.e5nlhep0.dati1w0a.j83agx80.btwxx1t3.lzcic4wl > div.rj1gh0hx.buofh1pr.ni8dbmo4.stjgntxs.rz4wbd8a > div._25-w > div > div > div > form > div > div > div._5rpb > div > div > div > div")
	private WebElement postTextbox;

	public FBCreatePostPage clickpostLink() {
		click(postLink, "clicking post link");
		return new FBCreatePostPage();
	}
	
	public FBHomePage createPost(String msg) {
		scrollToElement(entertopostText, "Scrolling to element");
		click(postTextbox,"clicking on the element");
		sendKeys(postTextbox, msg + Keys.ENTER, "Entering message as - " + msg);
		
		return this;
	}

}
