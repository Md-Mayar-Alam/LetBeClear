package com.letbeclear.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letbeclear.config.ImageConfig;
import com.letbeclear.domain.JwtUser;
import com.letbeclear.encoder.Decoder;
import com.letbeclear.model.UserPost;
import com.letbeclear.model.UserProfile;
import com.letbeclear.model.Users;
import com.letbeclear.repository.UserPostRepository;
import com.letbeclear.repository.UserProfileRepository;
import com.letbeclear.request.dto.ImageRequest;

@Service
public class ImageService {

	@Autowired 
	UserPostRepository userPostRepo;		
	@Autowired
	UserProfileRepository userProfileRepo;
	@Autowired
	ImageConfig imageConfig;
	@Autowired
	JwtUser jwtUser;
	
	public long addImage(ImageRequest imageRequest, String type)
	{
		
		Users users=new Users();				
		users.setUserId(jwtUser.getUserId());	
		UserPost userPost=new UserPost();			// creating user post object
			if(type.equals("postimage"))			//if type equals to postImage it will saved into the user_psot database
			{	
				String imagesPath="";		//for the path of the image
				String primaryPath="";		// for first showing image	
				int photoCount=0;			// for counting the number of photos
				List<String> photos=imageRequest.getImageAsString();
				if(photos.size()<=6)
				{
				Iterator<String> itrPhotos=photos.iterator();
			while(itrPhotos.hasNext())
			{
				Long postId=userPostRepo.getMax()+1;	//getting the last post_ID and increase by one for current post_Id 
				
				
				/*
				 *  Now creating the path for the images using the user_Id ,Post_Id and photoCount 
				 */
				
				String path="/images"+"/posts/"+"/timeline"+"/lbc_"+users.getUserId()+"_"+postId+"_"+photoCount+".jpg";
				String image=imageConfig.getPath()+path; 
				
				photoCount++;
				
				String imageAsString=itrPhotos.next();
				String decodedString=imageAsString.replaceFirst("data:image/jpeg;base64,","");
				
				System.out.println(decodedString);		// for bug
				
				Decoder.decoder(decodedString,image);
				imagesPath=imagesPath.concat(path+",");
				primaryPath=path;
			}
			
			userPost.setUsers(users);							//Set the data into the userPost object
			userPost.setImagePath(imagesPath);				
			userPost.setPrimaryImage(primaryPath);
			userPost.setNoOfImage(photoCount);
			userPost=userPostRepo.save(userPost);				//add that object into the repository	
			return userPost.getPostId();						//return the user_id for future use
				}
				else{
					return 0;
				}
			}
			
			
			if(type.equals("profile"))						//if type is profile the save data into the user_profile database
			{
				UserProfile userProfile=userProfileRepo.findByUserId(users.getUserId()); // get detail by the user_id for the future use
				Date dateOfBirth =userProfile.getDateofbirth();
				String displayName=userProfile.getDisplayname();
				String gender=userProfile.getGender();
				String maritalStatus=userProfile.getMaritalstatus();
			
				String path="/images"+"/posts"+"/profile"+"/lbc_"+users.getUserId()+".jpg";
				String image=imageConfig.getPath()+path;
				
				String decodedString=imageRequest.getImage().replaceFirst("data:image/jpeg;base64,","");
				
				System.out.println(decodedString);
				
			int status=	Decoder.decoder(decodedString,image);			//decode the users string for creating the image with give path
			if(status==1)
			{
			userProfile.setUserId(users.getUserId());					//Set the data into the userProfile object
			userProfile.setDateofbirth(dateOfBirth);
			userProfile.setDisplayname(displayName);
			userProfile.setGender(gender);
			userProfile.setMaritalstatus(maritalStatus);
			userProfile.setPhoto(path);
			userProfileRepo.save(userProfile);						//save all the data into the database 
			return 1;//1 for successful 
			}
			}
		return 0;   // zero for failure
	}

	
}
