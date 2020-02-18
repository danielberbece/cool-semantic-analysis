package cool.compiler.visitors;

import cool.compiler.ast.*;
import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ASTConstructionVisitor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        List<ASTClass> classes = new ArrayList<>();
        for (CoolParser.Class_Context classContext : ctx.class_()) {
            classes.add((ASTClass) visit(classContext));
        }

        return new ASTProg(classes);
    }

    @Override
    public ASTNode visitClass_(CoolParser.Class_Context ctx) {
        List<ASTFeature> features = new ArrayList<>();
        for (CoolParser.FeatureContext featureContext : ctx.feature()) {
            features.add((ASTFeature) visit(featureContext));
        }
        return new ASTClass(ctx, ctx.type, ctx.inheritType, features);
    }

    @Override
    public ASTNode visitNonExpr(CoolParser.NonExprContext ctx) {
        return new ASTComplement(ctx.NON().getSymbol(), (ASTExpr) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitCaseExpr(CoolParser.CaseExprContext ctx) {
        List<ASTExpr> exprs = new ArrayList<>();
        for (CoolParser.ExprContext exprContext : ctx.exprs) {
            exprs.add((ASTExpr) visit(exprContext));
        }

        return new ASTCaseExpr(
                ctx.CASE().getSymbol(),
                (ASTExpr) visit(ctx.caseExpr),
                ctx.ids,
                ctx.types,
                exprs
        );
    }

    @Override
    public ASTNode visitBlockExpr(CoolParser.BlockExprContext ctx) {
        List<ASTExpr> exprs = new ArrayList<>();
        for (CoolParser.ExprContext exprContext : ctx.expr()) {
            exprs.add((ASTExpr) visit(exprContext));
        }
        return new ASTBlockExpr(ctx.LBRACE().getSymbol(), exprs);
    }


    @Override
    public ASTNode visitLocal(CoolParser.LocalContext ctx) {
        if (ctx.expr() != null) {
            return new ASTLocalVar(
                    ctx.ID().getSymbol(),
                    ctx.TYPE().getSymbol(),
                    (ASTExpr) visit(ctx.expr())
            );
        } else {
            return new ASTLocalVar(
                    ctx.ID().getSymbol(),
                    ctx.TYPE().getSymbol(),
                    null
            );
        }
    }

    @Override
    public ASTNode visitLetExpr(CoolParser.LetExprContext ctx) {
        List<ASTLocalVar> locals = new ArrayList<>();
        for (CoolParser.LocalContext localContext : ctx.local()) {
            locals.add((ASTLocalVar) visit(localContext));
        }

        return new ASTLetExpr(
                ctx.LET().getSymbol(),
                locals,
                (ASTExpr) visit(ctx.body)
        );
    }

    @Override
    public ASTNode visitWhileExpr(CoolParser.WhileExprContext ctx) {
        return new ASTWhileExpr(
                ctx.WHILE().getSymbol(),
                (ASTExpr) visit(ctx.conditionExpr),
                (ASTExpr) visit(ctx.bodyExpr)
        );
    }

    @Override
    public ASTNode visitIfExpr(CoolParser.IfExprContext ctx) {
        return new ASTIfExpr(
                ctx.IF().getSymbol(),
                (ASTExpr) visit(ctx.ifExpr),
                (ASTExpr) visit(ctx.thenExpr),
                (ASTExpr) visit(ctx.elseExpr)
        );
    }

    @Override
    public ASTNode visitNewTypeExpr(CoolParser.NewTypeExprContext ctx) {
        return new ASTNewExpr(ctx.NEW().getSymbol(), ctx.TYPE().getSymbol());
    }

    @Override
    public ASTNode visitAssignExpr(CoolParser.AssignExprContext ctx) {
        return new ASTAssignExpr(ctx.ASSIGN().getSymbol(), ctx.ID().getSymbol(), (ASTExpr) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitDotExpr(CoolParser.DotExprContext ctx) {
        List<ASTExpr> exprs = new ArrayList<>();
        for (CoolParser.ExprContext exprContext : ctx.exprs) {
            exprs.add((ASTExpr) visit(exprContext));
        }
        Token typeToken = null;
        if (ctx.TYPE() != null) typeToken = ctx.TYPE().getSymbol();

        return new ASTExplicitDispatch(
                (ASTExpr) visit(ctx.object),
                typeToken,
                ctx.ID().getSymbol(),
                exprs
        );
    }

    @Override
    public ASTNode visitDispatchExpr(CoolParser.DispatchExprContext ctx) {
        List<ASTExpr> exprs = new ArrayList<>();
        for (CoolParser.ExprContext exprContext : ctx.exprs) {
            exprs.add((ASTExpr) visit(exprContext));
        }
        return new ASTImplicitDispatchExpr(ctx.ID().getSymbol(), exprs);
    }

    @Override
    public ASTNode visitIsvoidExpr(CoolParser.IsvoidExprContext ctx) {
        return new ASTIsvoidExpr(ctx.ISVOID().getSymbol(), (ASTExpr) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitLtExpr(CoolParser.LtExprContext ctx) {
        return new ASTLessThanExpr(ctx.LT().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitEqualExpr(CoolParser.EqualExprContext ctx) {
        return new ASTEqualExpr(ctx.EQUAL().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitLeExpr(CoolParser.LeExprContext ctx) {
        return new ASTLessEqualExpr(ctx.LE().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitNotExpr(CoolParser.NotExprContext ctx) {
        return new ASTNotExpr(ctx.NOT().getSymbol(), (ASTExpr) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitParenExpr(CoolParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ASTNode visitMultExpr(CoolParser.MultExprContext ctx) {
        return new ASTMult(ctx.MULT().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitDivExpr(CoolParser.DivExprContext ctx) {
        return new ASTDiv(ctx.DIV().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitPlusExpr(CoolParser.PlusExprContext ctx) {
        return new ASTPlus(ctx.PLUS().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitMinusExpr(CoolParser.MinusExprContext ctx) {
        return new ASTMinus(ctx.MINUS().getSymbol(), (ASTExpr) visit(ctx.expr(0)), (ASTExpr) visit(ctx.expr(1)));
    }

    @Override
    public ASTNode visitStringExpr(CoolParser.StringExprContext ctx) {
        return new ASTString(ctx.STRING().getSymbol());
    }

    @Override
    public ASTNode visitBoolExpr(CoolParser.BoolExprContext ctx) {
        return new ASTBool(ctx.BOOL().getSymbol());
    }

    @Override
    public ASTNode visitAttribute(CoolParser.AttributeContext ctx) {
        if (ctx.expr() != null) {
            return new ASTAttribute(ctx.id, ctx.type, (ASTExpr) visit(ctx.expr()));
        } else {
            return new ASTAttribute(ctx.id, ctx.type, null);
        }
    }

    @Override
    public ASTNode visitMethod(CoolParser.MethodContext ctx) {
        List<ASTFormal> formals = new ArrayList<>();
        for (CoolParser.FormalContext formalContext : ctx.formals) {
            formals.add((ASTFormal) visit(formalContext));
        }

        return new ASTMethod(ctx.id, formals, ctx.type, (ASTExpr) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitIdExpr(CoolParser.IdExprContext ctx) {
        return new ASTId(ctx.ID().getSymbol());
    }

    @Override
    public ASTNode visitIntExpr(CoolParser.IntExprContext ctx) {
        return new ASTInt(ctx.INT().getSymbol());
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new ASTFormal(ctx.name, ctx.type);
    }
}
