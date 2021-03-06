/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.ast.expr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLExprImpl;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class SQLObjectCreateExpr extends SQLExprImpl implements Serializable {

    private static final long  serialVersionUID = 1L;
    public String              objType;
    public final List<SQLExpr> paramList        = new ArrayList<SQLExpr>();

    public SQLObjectCreateExpr(){

    }

    public SQLObjectCreateExpr(String objType){

        this.objType = objType;
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        this.accept0((OracleASTVisitor) visitor);
    }

    protected void accept0(OracleASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.paramList);
        }

        visitor.endVisit(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((objType == null) ? 0 : objType.hashCode());
        result = prime * result + ((paramList == null) ? 0 : paramList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SQLObjectCreateExpr other = (SQLObjectCreateExpr) obj;
        if (objType == null) {
            if (other.objType != null) {
                return false;
            }
        } else if (!objType.equals(other.objType)) {
            return false;
        }
        if (paramList == null) {
            if (other.paramList != null) {
                return false;
            }
        } else if (!paramList.equals(other.paramList)) {
            return false;
        }
        return true;
    }
}
