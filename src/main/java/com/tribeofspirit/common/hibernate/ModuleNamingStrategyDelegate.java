package com.tribeofspirit.common.hibernate;

import org.hibernate.cfg.naming.NamingStrategyDelegate;

import org.hibernate.internal.util.StringHelper;

import java.io.Serializable;

/**
 * Author : gonwang
 * Create Time : 05.07.2015.
 */
public class ModuleNamingStrategyDelegate  implements NamingStrategyDelegate, Serializable {

    @Override
    public String determineImplicitPrimaryTableName(String entityName, String jpaEntityName) {
        return getModuleName(entityName) + "_" + addUnderscores(StringHelper.unqualify(entityName));
    }

    @Override
    public String determineImplicitPropertyColumnName(String propertyPath) {
        return addUnderscores(StringHelper.unqualify(propertyPath));
    }

    @Override
    public String determineImplicitElementCollectionTableName(String ownerEntityName, String ownerJpaEntityName, String ownerEntityTable, String propertyPath) {
        return addUnderscores(StringHelper.unqualify(propertyPath));
    }

    @Override
    public String determineImplicitElementCollectionJoinColumnName(String ownerEntityName, String ownerJpaEntityName, String ownerEntityTable, String referencedColumnName, String propertyPath) {
        if (propertyPath != null) {
            return addUnderscores(propertyPath) + "_id";
        } else {
            return addUnderscores(StringHelper.unqualifyEntityName(ownerEntityName)) + "_id";
        }
    }

    @Override
    public String determineImplicitEntityAssociationJoinTableName(String ownerEntityName, String ownerJpaEntityName, String ownerEntityTable, String associatedEntityName, String associatedJpaEntityName, String associatedEntityTable, String propertyPath) {
        return addUnderscores(StringHelper.unqualify(propertyPath));
    }

    @Override
    public String determineImplicitEntityAssociationJoinColumnName(String propertyEntityName, String propertyJpaEntityName, String propertyTableName, String referencedColumnName, String propertyPath) {
        if (propertyPath != null) {
            return addUnderscores(propertyPath) + "_id";
        } else {
            return addUnderscores(StringHelper.unqualifyEntityName(propertyTableName)) + "_id";
        }
    }

    @Override
    public String toPhysicalJoinKeyColumnName(String joinedColumn, String joinedTable) {
        return joinedColumn;
    }

    @Override
    public String determineLogicalColumnName(String columnName, String propertyName) {
        return StringHelper.isNotEmpty( columnName ) ? columnName : StringHelper.unqualify( propertyName );
    }

    @Override
    public String determineLogicalElementCollectionTableName(String tableName, String ownerEntityName, String ownerJpaEntityName, String ownerEntityTable, String propertyName) {
        if ( tableName != null ) {
            return tableName;
        }
        else {
            //use of a stringbuffer to workaround a JDK bug
            return ownerEntityTable + "_" + StringHelper.unqualify(propertyName);
        }
    }

    @Override
    public String determineLogicalEntityAssociationJoinTableName(String tableName, String ownerEntityName, String ownerJpaEntityName, String ownerEntityTable, String associatedEntityName, String associatedJpaEntityName, String associatedEntityTable, String propertyName) {
        if ( tableName != null ) {
            return tableName;
        }
        else {
            //use of a stringbuffer to workaround a JDK bug
            return ownerEntityTable + "_" + (associatedEntityTable != null ?
                    associatedEntityTable :
                    StringHelper.unqualify(propertyName));
        }
    }

    @Override
    public String determineLogicalCollectionColumnName(String columnName, String propertyName, String referencedColumn) {
        return StringHelper.isNotEmpty( columnName ) ? columnName : propertyName + "_" + referencedColumn;
    }

    @Override
    public String toPhysicalTableName(String tableName) {
        return tableName;
    }

    @Override
    public String toPhysicalColumnName(String columnName) {
        return columnName;
    }

    private String addUnderscores(String name) {
        StringBuffer buf = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if ('_' != buf.charAt(i - 1) && Character.isUpperCase(buf.charAt(i)) && !Character.isUpperCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }

    private static String getModuleName(String className) {

        int secondDotIndex = className.indexOf('.', className.indexOf('.') + 1);
        int thirdDotIndex = className.indexOf('.', secondDotIndex + 1);
        int fourthDotIndex = className.indexOf('.', thirdDotIndex + 1);

        return className.substring(thirdDotIndex + 1, fourthDotIndex).toUpperCase();
    }
}
