/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dropbox;

/**
 *
 * @author ASUS
 */
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.DbxUserUsersRequests;
import com.dropbox.core.v2.users.FullAccount;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

public class Main {
    private static final String ACCESS_TOKEN = "sl.AsXGuyEutLdS9LAhYjz61Fg6iJoKD_CtMixhTylNvN_wyffLHfTcpyzQX66cZtaAKufdQ5xyIZwNUMOUyqgD5S62qAUIYzzL9-TSOA-1QV3j81flZ1JtvXHXD9AbTn0S7Y6IcEvh4zE";

   public static void main(String args[]) throws FileNotFoundException, IOException, DbxException {
		System.out.println("Hi");
		
		
			
			DbxRequestConfig config;
			config = new DbxRequestConfig("/dropbox/folder");
			DbxClientV2 client;
			client = new DbxClientV2(config, ACCESS_TOKEN);
                       // FullAccount account = client.users().getCurrentAccount();
			FullAccount account;
			DbxUserUsersRequests r1 = client.users();
			account = r1.getCurrentAccount();
			System.out.println(account.getName().getDisplayName());
			
			// Get files and folder metadata from Dropbox root directory
			ListFolderResult result = client.files().listFolder("");
			while (true) {
				for (Metadata metadata : result.getEntries()) {
					System.out.println("hello "+metadata.getPathLower());
				}
				
				if (!result.getHasMore()) {
					break;
				}
				
				result = client.files().listFolderContinue(result.getCursor());
			}
                     
		
        // Upload "test.txt" to Dropbox
       try (InputStream in = new FileInputStream("abc.txt")) { FileMetadata metadata = client.files().uploadBuilder("/folder3/abc.txt")
                .uploadAndFinish(in);
        }catch(DbxException e){}
       
                }
}