package clikmng.nanet.go.kr.mdm.web;

import clikmng.nanet.go.kr.mdm.utl.MdmFtp;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MdmFtp ftp = new MdmFtp();
		ftp.ftp("/clikapi-file/clik001/2015/055001/20150129/RES403/192_(의안_668)_동남권광역연합_추진_특별위원회_활동결과_보고서_채택의_건최종.PDF", "D:/works/temp/다운로드.pdf");
		
		
	}

}
