/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package org.teiid.metadata;

import java.util.Collections;
import org.teiid.core.types.DataTypeManagerService;
import org.teiid.core.util.StringUtil;
import org.teiid.designer.annotation.Since;
import org.teiid.designer.runtime.version.spi.ITeiidServerVersion;
import org.teiid.designer.runtime.version.spi.TeiidServerVersion.Version;


public abstract class BaseColumn extends AbstractMetadataRecord {
	
	private static final long serialVersionUID = 6382258617714856616L;

	public enum NullType {
		No_Nulls {
			@Override
			public String toString() {
				return "No Nulls"; //$NON-NLS-1$
			}
		},
		Nullable,
		Unknown		
	}

    private final ITeiidServerVersion teiidVersion;

	private String datatypeUUID;
    private String runtimeType;
    private String defaultValue;
    private int length;
    private int scale;
    private int radix;
    private int precision;
    private NullType nullType;
    private int position;
    private Datatype datatype;
    @Since(Version.TEIID_8_7)
    private int arrayDimensions;
    @Since(Version.TEIID_8_7)
    private String nativeType;

    public BaseColumn(ITeiidServerVersion teiidVersion) {
        this.teiidVersion = teiidVersion;
    }

    /**
     * @return the teiidVersion
     */
    public ITeiidServerVersion getTeiidVersion() {
        return this.teiidVersion;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getDatatypeUUID() {
        return datatypeUUID;
    }

    public String getRuntimeType() {
        return runtimeType;
    }
    
    public Class<?> getJavaType() {
        return DataTypeManagerService.getInstance(teiidVersion).getDataTypeClass(runtimeType);
    }

    public int getLength() {
        return length;
    }

    public int getPrecision() {
        return precision;
    }

    public int getScale() {
        return scale;
    }

    public int getRadix() {
        return radix;
    }

    /**
     * 1 based ordinal position
     * @return
     */
    public int getPosition() {
        return position;
    }

    public NullType getNullType() {
    	if (nullType == null) {
    		return NullType.Unknown;
    	}
        return nullType;
    }

	public void setLength(int i) {
		length = i;
	}

	public void setPrecision(int i) {
		precision = i;
	}

	public void setScale(int i) {
		scale = i;
	}

	public void setRadix(int i) {
		radix = i;
	}

    public void setNullType(NullType i) {
        nullType = i;
    }

	public void setPosition(int i) {
		position = i;
	}

	public void setRuntimeType(String string) {
		runtimeType = string;
	}

	public void setDatatypeUUID(String string) {
		datatypeUUID = string;
	}

	public void setDefaultValue(String object) {
		defaultValue = object;
	}

    public Datatype getDatatype() {
		return datatype;
	}
    
    public void setDatatype(Datatype datatype) {
    	setDatatype(datatype, false, 0);
    }
    
    public void setDatatype(Datatype datatype, boolean copyAttributes) {
    	setDatatype(datatype, copyAttributes, 0);
    }
    
    public void setDatatype(Datatype datatype, boolean copyAttributes, int arrayDimensions) {
		this.datatype = datatype;
		this.arrayDimensions = arrayDimensions;
		if (datatype != null) {
			this.datatypeUUID = this.datatype.getUUID();
			this.runtimeType = this.datatype.getRuntimeTypeName();
			if (arrayDimensions > 0) {
				this.runtimeType += StringUtil.join(Collections.nCopies(arrayDimensions, "[]"), ""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (copyAttributes) {
				this.radix = this.datatype.getRadix();
				this.length = this.datatype.getLength();
				this.precision = this.datatype.getPrecision();
				this.scale = this.datatype.getScale();
				this.nullType = this.datatype.getNullType();
			}
		}
    }

    /**
     * Get the array dimensions.
     * @return
     */
    public int getArrayDimensions() {
		return arrayDimensions;
	}

	@Since(Version.TEIID_8_7)
    public String getNativeType() {
        return nativeType;
    }

    /**
     * @param nativeType The nativeType to set.
     * @since 4.2
     */
	@Since(Version.TEIID_8_7)
    public void setNativeType(String nativeType) {
        this.nativeType = nativeType;
    }
}
