/*
 * Copyright 2011 Jon S Akhtar (Sylvanaar)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.sylvanaar.idea.Lua.lang.psi.impl.statements;

import com.intellij.lang.ASTNode;
import com.sylvanaar.idea.Lua.lang.psi.expressions.LuaExpressionList;
import com.sylvanaar.idea.Lua.lang.psi.expressions.LuaFunctionCallExpression;
import com.sylvanaar.idea.Lua.lang.psi.statements.LuaRequireStatement;

/**
 * Created by IntelliJ IDEA.
 * User: Jon S Akhtar
 * Date: 3/7/11
 * Time: 11:23 AM
 */
public class LuaRequireStatementImpl extends LuaFunctionCallStatementImpl implements LuaRequireStatement {
    public LuaRequireStatementImpl(ASTNode node) {
        super(node);
    }

    @Override
    public String toString() {
        return "Require Stmt: " + (getName()!=null?getName():"null");
    }

    @Override
    public String getName() {
        LuaFunctionCallExpression expr = getInvokedExpression();

        if (expr == null) return "";

        LuaExpressionList argumentList = expr.getArgumentList();

        if (argumentList == null) return "";

        return  argumentList.getLuaExpressions().get(0).getText();
    }
}