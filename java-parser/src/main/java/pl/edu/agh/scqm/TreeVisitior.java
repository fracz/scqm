package pl.edu.agh.scqm;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.BlockComment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.modules.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.VoidVisitor;

import javax.annotation.Generated;

/**
 * @see https://github.com/javaparser/javaparser/issues/538
 */
public abstract class TreeVisitior implements VoidVisitor<Integer> {

    public abstract void in(Node n, Integer indentLevel);

    public abstract void out(Node n, Integer indentLevel);

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final AnnotationDeclaration n, final Integer arg) {
        in(n, arg);
        n.getMembers().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final AnnotationMemberDeclaration n, final Integer arg) {
        in(n, arg);
        n.getDefaultValue().ifPresent(l -> l.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ArrayAccessExpr n, final Integer arg) {
        in(n, arg);
        n.getIndex().accept(this, arg + 1);
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ArrayCreationExpr n, final Integer arg) {
        in(n, arg);
        n.getElementType().accept(this, arg + 1);
        n.getInitializer().ifPresent(l -> l.accept(this, arg + 1));
        n.getLevels().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ArrayInitializerExpr n, final Integer arg) {
        in(n, arg);
        n.getValues().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final AssertStmt n, final Integer arg) {
        in(n, arg);
        n.getCheck().accept(this, arg + 1);
        n.getMessage().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final AssignExpr n, final Integer arg) {
        in(n, arg);
        n.getTarget().accept(this, arg + 1);
        n.getValue().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final BinaryExpr n, final Integer arg) {
        in(n, arg);
        n.getLeft().accept(this, arg + 1);
        n.getRight().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final BlockComment n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final BlockStmt n, final Integer arg) {
        in(n, arg);
        n.getStatements().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final BooleanLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final BreakStmt n, final Integer arg) {
        in(n, arg);
        n.getLabel().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final CastExpr n, final Integer arg) {
        in(n, arg);
        n.getExpression().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final CatchClause n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getParameter().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final CharLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ClassExpr n, final Integer arg) {
        in(n, arg);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ClassOrInterfaceDeclaration n, final Integer arg) {
        in(n, arg);
        n.getExtendedTypes().forEach(p -> p.accept(this, arg + 1));
        n.getImplementedTypes().forEach(p -> p.accept(this, arg + 1));
        n.getTypeParameters().forEach(p -> p.accept(this, arg + 1));
        n.getMembers().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ClassOrInterfaceType n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getScope().ifPresent(l -> l.accept(this, arg + 1));
        n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final CompilationUnit n, final Integer arg) {
        in(n, arg);
        n.getImports().forEach(p -> p.accept(this, arg + 1));
        n.getModule().ifPresent(l -> l.accept(this, arg + 1));
        n.getPackageDeclaration().ifPresent(l -> l.accept(this, arg + 1));
        n.getTypes().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ConditionalExpr n, final Integer arg) {
        in(n, arg);
        n.getCondition().accept(this, arg + 1);
        n.getElseExpr().accept(this, arg + 1);
        n.getThenExpr().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ConstructorDeclaration n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getName().accept(this, arg + 1);
        n.getParameters().forEach(p -> p.accept(this, arg + 1));
        n.getReceiverParameter().ifPresent(l -> l.accept(this, arg + 1));
        n.getThrownExceptions().forEach(p -> p.accept(this, arg + 1));
        n.getTypeParameters().forEach(p -> p.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ContinueStmt n, final Integer arg) {
        in(n, arg);
        n.getLabel().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final DoStmt n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getCondition().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final DoubleLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final EmptyStmt n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final EnclosedExpr n, final Integer arg) {
        in(n, arg);
        n.getInner().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final EnumConstantDeclaration n, final Integer arg) {
        in(n, arg);
        n.getArguments().forEach(p -> p.accept(this, arg + 1));
        n.getClassBody().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final EnumDeclaration n, final Integer arg) {
        in(n, arg);
        n.getEntries().forEach(p -> p.accept(this, arg + 1));
        n.getImplementedTypes().forEach(p -> p.accept(this, arg + 1));
        n.getMembers().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ExplicitConstructorInvocationStmt n, final Integer arg) {
        in(n, arg);
        n.getArguments().forEach(p -> p.accept(this, arg + 1));
        n.getExpression().ifPresent(l -> l.accept(this, arg + 1));
        n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ExpressionStmt n, final Integer arg) {
        in(n, arg);
        n.getExpression().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final FieldAccessExpr n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getScope().accept(this, arg + 1);
        n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final FieldDeclaration n, final Integer arg) {
        in(n, arg);
        n.getVariables().forEach(p -> p.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ForeachStmt n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getIterable().accept(this, arg + 1);
        n.getVariable().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ForStmt n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getCompare().ifPresent(l -> l.accept(this, arg + 1));
        n.getInitialization().forEach(p -> p.accept(this, arg + 1));
        n.getUpdate().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final IfStmt n, final Integer arg) {
        in(n, arg);
        n.getCondition().accept(this, arg + 1);
        n.getElseStmt().ifPresent(l -> l.accept(this, arg + 1));
        n.getThenStmt().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final InitializerDeclaration n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final InstanceOfExpr n, final Integer arg) {
        in(n, arg);
        n.getExpression().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final IntegerLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final JavadocComment n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final LabeledStmt n, final Integer arg) {
        in(n, arg);
        n.getLabel().accept(this, arg + 1);
        n.getStatement().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final LineComment n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final LongLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final MarkerAnnotationExpr n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final MemberValuePair n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getValue().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final MethodCallExpr n, final Integer arg) {
        in(n, arg);
        n.getArguments().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getScope().ifPresent(l -> l.accept(this, arg + 1));
        n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final MethodDeclaration n, final Integer arg) {
        in(n, arg);
        n.getBody().ifPresent(l -> l.accept(this, arg + 1));
        n.getType().accept(this, arg + 1);
        n.getName().accept(this, arg + 1);
        n.getParameters().forEach(p -> p.accept(this, arg + 1));
        n.getReceiverParameter().ifPresent(l -> l.accept(this, arg + 1));
        n.getThrownExceptions().forEach(p -> p.accept(this, arg + 1));
        n.getTypeParameters().forEach(p -> p.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final NameExpr n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final NormalAnnotationExpr n, final Integer arg) {
        in(n, arg);
        n.getPairs().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final NullLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ObjectCreationExpr n, final Integer arg) {
        in(n, arg);
        n.getAnonymousClassBody().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getArguments().forEach(p -> p.accept(this, arg + 1));
        n.getScope().ifPresent(l -> l.accept(this, arg + 1));
        n.getType().accept(this, arg + 1);
        n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final PackageDeclaration n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final Parameter n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getVarArgsAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final PrimitiveType n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final Name n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getQualifier().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final SimpleName n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ArrayType n, final Integer arg) {
        in(n, arg);
        n.getComponentType().accept(this, arg + 1);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ArrayCreationLevel n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getDimension().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final IntersectionType n, final Integer arg) {
        in(n, arg);
        n.getElements().forEach(p -> p.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final UnionType n, final Integer arg) {
        in(n, arg);
        n.getElements().forEach(p -> p.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ReturnStmt n, final Integer arg) {
        in(n, arg);
        n.getExpression().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final SingleMemberAnnotationExpr n, final Integer arg) {
        in(n, arg);
        n.getMemberValue().accept(this, arg + 1);
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final StringLiteralExpr n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final SuperExpr n, final Integer arg) {
        in(n, arg);
        n.getClassExpr().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final SwitchEntryStmt n, final Integer arg) {
        in(n, arg);
        n.getLabel().ifPresent(l -> l.accept(this, arg + 1));
        n.getStatements().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final SwitchStmt n, final Integer arg) {
        in(n, arg);
        n.getEntries().forEach(p -> p.accept(this, arg + 1));
        n.getSelector().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final SynchronizedStmt n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getExpression().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ThisExpr n, final Integer arg) {
        in(n, arg);
        n.getClassExpr().ifPresent(l -> l.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ThrowStmt n, final Integer arg) {
        in(n, arg);
        n.getExpression().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final TryStmt n, final Integer arg) {
        in(n, arg);
        n.getCatchClauses().forEach(p -> p.accept(this, arg + 1));
        n.getFinallyBlock().ifPresent(l -> l.accept(this, arg + 1));
        n.getResources().forEach(p -> p.accept(this, arg + 1));
        n.getTryBlock().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final LocalClassDeclarationStmt n, final Integer arg) {
        in(n, arg);
        n.getClassDeclaration().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final TypeParameter n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getTypeBound().forEach(p -> p.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final UnaryExpr n, final Integer arg) {
        in(n, arg);
        n.getExpression().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final UnknownType n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final VariableDeclarationExpr n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getVariables().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final VariableDeclarator n, final Integer arg) {
        in(n, arg);
        n.getInitializer().ifPresent(l -> l.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final VoidType n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final WhileStmt n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getCondition().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final WildcardType n, final Integer arg) {
        in(n, arg);
        n.getExtendedType().ifPresent(l -> l.accept(this, arg + 1));
        n.getSuperType().ifPresent(l -> l.accept(this, arg + 1));
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final LambdaExpr n, final Integer arg) {
        in(n, arg);
        n.getBody().accept(this, arg + 1);
        n.getParameters().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final MethodReferenceExpr n, final Integer arg) {
        in(n, arg);
        n.getScope().accept(this, arg + 1);
        n.getTypeArguments().ifPresent(l -> l.forEach(v -> v.accept(this, arg + 1)));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final TypeExpr n, final Integer arg) {
        in(n, arg);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    public void visit(NodeList n, Integer arg) {
        for (Object node : n) {
            ((Node) node).accept(this, arg + 1);
        }
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ImportDeclaration n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ModuleDeclaration n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getModuleStmts().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ModuleRequiresStmt n, final Integer arg) {
        in(n, arg);
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ModuleExportsStmt n, final Integer arg) {
        in(n, arg);
        n.getModuleNames().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ModuleProvidesStmt n, final Integer arg) {
        in(n, arg);
        n.getType().accept(this, arg + 1);
        n.getWithTypes().forEach(p -> p.accept(this, arg + 1));
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ModuleUsesStmt n, final Integer arg) {
        in(n, arg);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ModuleOpensStmt n, final Integer arg) {
        in(n, arg);
        n.getModuleNames().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final UnparsableStmt n, final Integer arg) {
        in(n, arg);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
        out(n, arg);
    }

    @Override
    @Generated("com.github.javaparser.generator.core.visitor.VoidVisitorAdapterGenerator")
    public void visit(final ReceiverParameter n, final Integer arg) {
        in(n, arg);
        n.getAnnotations().forEach(p -> p.accept(this, arg + 1));
        n.getName().accept(this, arg + 1);
        n.getType().accept(this, arg + 1);
        n.getComment().ifPresent(l -> l.accept(this, arg + 1));
    }
}
