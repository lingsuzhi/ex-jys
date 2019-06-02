package com.lsz.jys.apply.dto.admin;

public class ImageCodeVO {

	/**
	 * 图片验证码
	 */
	private String image;

	/**
	 * 公钥
	 */
	private String publicKey;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

}
