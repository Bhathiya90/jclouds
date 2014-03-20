package org.jclouds.blobstore.integration.internal;


import java.io.IOException;

import org.jclouds.blobstore.BlobStore;
import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.functions.ObjectMD5;
import org.testng.annotations.Test;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;


@Test(groups = "live")
public class BaseBlobMD5LiveTest extends BaseBlobStoreIntegrationTest{
	
	public void putBlobWithCorrectMD5() throws IOException, InterruptedException  {
		String blobName = "putBlobWithCorrectMD5";
		BlobStore blobStore = view.getBlobStore();
		String containerName = getContainerName();
		
		blobStore.createContainerInLocation(null, getContainerName());		
		ByteSource payload = ByteSource.wrap("blob-content".getBytes(Charsets.UTF_8));		
		byte [] md5 = new ObjectMD5().apply(payload);
		
		Blob blob = blobStore.blobBuilder(blobName)  
		    .payload(payload).contentMD5(md5)
		    .contentLength(payload.size())
		    .build();	
	    blobStore.putBlob(containerName, blob);
	}
	
	 public void putBlobWithIncorrectMD5() throws InterruptedException, IOException{
		
		String blobName = "putBlobWithIncorrectMD5";
		BlobStore blobStore = view.getBlobStore();
		String containerName = getContainerName();
		
		blobStore.createContainerInLocation(null, getContainerName());		
		ByteSource payload = ByteSource.wrap("blob-content".getBytes(Charsets.UTF_8));	
		ByteSource payload_wrong = payload.slice(1, payload.size());
		byte [] md5_wrong = new ObjectMD5().apply(payload_wrong);
		
		
		Blob blob = blobStore.blobBuilder(blobName)  
		    .payload(payload).contentMD5(md5_wrong)
		    .contentLength(payload.size())
		    .build();	
	   
		blobStore.putBlob(containerName, blob);
		
	}
	
	public void putBlobWithoutMD5() throws InterruptedException, IOException{
		
		String blobName = "putBlobWithoutMD5";
		BlobStore blobStore = view.getBlobStore();
		String containerName = getContainerName();
		
		blobStore.createContainerInLocation(null, getContainerName());		
		ByteSource payload = ByteSource.wrap("blob-content".getBytes(Charsets.UTF_8));	
			
		Blob blob = blobStore.blobBuilder(blobName)  
		    .payload(payload)
		    .contentLength(payload.size())
		    .build();	
	   
		blobStore.putBlob(containerName, blob);
		
	}
	 
	public void signedUrlPutWithCorrectMD5(){
		
	}
	
	public void signedUrlPutWithIncorrectMD5(){
		
	}

}
