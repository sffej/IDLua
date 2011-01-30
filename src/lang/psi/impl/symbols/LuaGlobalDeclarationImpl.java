/*
 * Copyright 2011 Jon S Akhtar (Sylvanaar)
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
 */

package com.sylvanaar.idea.Lua.lang.psi.impl.symbols;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiType;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.util.IncorrectOperationException;
import com.sylvanaar.idea.Lua.lang.psi.expressions.LuaDeclarationExpression;
import com.sylvanaar.idea.Lua.lang.psi.expressions.LuaExpression;
import com.sylvanaar.idea.Lua.lang.psi.symbols.LuaGlobalDeclaration;
import com.sylvanaar.idea.Lua.lang.psi.symbols.LuaIdentifier;
import com.sylvanaar.idea.Lua.lang.psi.symbols.LuaSymbol;
import com.sylvanaar.idea.Lua.lang.psi.visitor.LuaElementVisitor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Jon S Akhtar
 * Date: 1/15/11
 * Time: 1:31 AM
 */
public class LuaGlobalDeclarationImpl extends LuaPsiDeclarationReferenceElementImpl  //LuaPsiBaseElementImpl<LuaGlobalDeclarationStub>
        implements LuaGlobalDeclaration, LuaDeclarationExpression {
    public LuaGlobalDeclarationImpl(ASTNode node) {
        super(node);
    }

//    public LuaGlobalDeclarationImpl(LuaGlobalDeclarationStub stub) {
//        super(stub, LuaElementTypes.GLOBAL_NAME_DECL);
//    }

    @Override
    public String toString() {
        return "Global Decl: " + getText();
    }

    @Override
    public LuaIdentifier getNameSymbol() {
        return this;
    }

    @Override
    public String getDefinedName() {
//        final LuaGlobalDeclarationStub stub = (LuaGlobalDeclarationStub) getStub();
//        if (stub != null) {
//            return stub.getName();
//        }

        return getName();
    }


    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor processor,
                                       @NotNull ResolveState state, PsiElement lastParent,
                                       @NotNull PsiElement place) {
        return processor.execute(this, state);
    }

//    @Override
//    public String getName() {
//        final LuaGlobalDeclarationStub stub = (LuaGlobalDeclarationStub) getStub();
//        if (stub != null) {
//            return stub.getName();
//        }
//
//        return super.getName();    //To change body of overridden methods use File | Settings | File Templates.
//    }

    @Override
    public void accept(LuaElementVisitor visitor) {
        visitor.visitDeclarationExpression(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof LuaElementVisitor) {
            ((LuaElementVisitor) visitor).visitDeclarationExpression(this);
        } else {
            visitor.visitElement(this);
        }
    }

    @Override
    public PsiElement replaceWithExpression(LuaExpression newCall, boolean b) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiType getType() {
        return PsiType.VOID;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement setName(@NonNls String name) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

//    @NotNull
//    @Override
//    public IStubElementType getElementType() {
//        return LuaElementTypes.GLOBAL_NAME_DECL;
//    }

    @Override
    public boolean isSameKind(LuaSymbol symbol) {
        return symbol instanceof LuaGlobalUsageImpl;
    }

    @Override
    public boolean isAssignedTo() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}