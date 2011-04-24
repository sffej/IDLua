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

package com.sylvanaar.idea.Lua.lang.psi.stubs.elements;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.io.StringRef;
import com.sylvanaar.idea.Lua.lang.psi.expressions.LuaFieldIdentifier;
import com.sylvanaar.idea.Lua.lang.psi.impl.symbols.LuaFieldIdentifierImpl;
import com.sylvanaar.idea.Lua.lang.psi.impl.symbols.LuaGlobalDeclarationImpl;
import com.sylvanaar.idea.Lua.lang.psi.stubs.LuaStubElementType;
import com.sylvanaar.idea.Lua.lang.psi.stubs.api.LuaGlobalDeclarationStub;
import com.sylvanaar.idea.Lua.lang.psi.stubs.impl.LuaFieldStub;
import com.sylvanaar.idea.Lua.lang.psi.stubs.impl.LuaGlobalDeclarationStubImpl;
import com.sylvanaar.idea.Lua.lang.psi.stubs.index.LuaFieldIndex;
import com.sylvanaar.idea.Lua.lang.psi.stubs.index.LuaGlobalDeclarationIndex;
import com.sylvanaar.idea.Lua.lang.psi.symbols.LuaGlobalDeclaration;

import java.io.IOException;

/**
* Created by IntelliJ IDEA.
* User: Jon S Akhtar
* Date: 1/23/11
* Time: 8:01 PM
*/
public class LuaFieldStubType
        extends LuaStubElementType<LuaFieldStub, LuaFieldIdentifier> {

    public LuaFieldStubType() {
        super("field name stub");
    }

    @Override
    public LuaFieldIdentifier createPsi(LuaFieldStub stub) {
        return new LuaFieldIdentifierImpl(stub);
    }

    @Override
    public LuaFieldStub createStub(LuaFieldIdentifier psi, StubElement parentStub) {
        return new LuaFieldStub(parentStub, StringRef.fromString(psi.getName()));
    }

    @Override
    public void serialize(LuaFieldStub stub, StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @Override
    public LuaFieldStub deserialize(StubInputStream dataStream, StubElement parentStub) throws IOException {
        StringRef ref = dataStream.readName();
        return new LuaFieldStub(parentStub, ref);
    }

    @Override
    public String getExternalId() {
        return "lua.FIELD";
    }

    @Override
    public void indexStub(LuaFieldStub stub, IndexSink sink) {
        String name = stub.getName();
        
        if (name != null) {
          sink.occurrence(LuaFieldIndex.KEY, name);
        }
    }

    @Override
    public PsiElement createElement(ASTNode node) {
        return new LuaFieldIdentifierImpl(node);
    }
}