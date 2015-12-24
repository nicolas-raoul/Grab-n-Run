/*******************************************************************************
 * Copyright 2014 Luca Falsina
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package it.necst.grabnrun;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileFilter;

/**
 * CertFileFilter is a filter for checking local files in a directory and 
 * verify whether these element are certificate files by holding one of 
 * the correct extensions (.pem). Moreover it also verifies that the name 
 * of the certificate matches the one provided during the constructor
 * invocation.
 * 
 * @author Luca Falsina
 */
final class CertFileFilter implements FileFilter {

    private final static String PEM_EXT = ".pem";
	private final static String[] okCertsExtensions = new String[]{PEM_EXT};
    private final String certificateName;
	
	/**
	 * A constructor for the filter which receives the 
	 * name of the desired certificate as a parameter.
	 * <p>
	 * Do not provide the extension of the certificate
	 * file but only the name!
	 * 
	 * @param certificateName
	 *  the name of the certificate file.
	 */
	CertFileFilter(@NonNull String certificateName) {
		this.certificateName = checkNotNull(
                certificateName, "The input name for the certificate was null.");
	}
	
	@Override
	public final boolean accept(@NonNull File file) {
        checkNotNull(file, "The input file descriptor was null.");
		if (file.isDirectory())
			return false;
		else if (file.isFile()) {
			// On the contrary if this is a normal file and its name is
			// the desired one and it ends with one of the 
			// approved extensions then it's fine.
			for (String extension : okCertsExtensions) {
				if (file.getName().equals(certificateName + extension))
		    	  return true;
		    }
		}
		
		// Used for any other kind of weird stuff reaching the filter..
		return false;
	}
}
