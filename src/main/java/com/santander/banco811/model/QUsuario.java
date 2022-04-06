package com.santander.banco811.model;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

public class QUsuario extends EntityPathBase<Usuario> {

    public static final QUsuario Usuario = new QUsuario("usuario");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final StringPath cpf = createString("cpf");

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Class<? extends Usuario> type, String variable) {
        super(type, variable);
    }

    public QUsuario(Class<? extends Usuario> type, PathMetadata metadata) {
        super(type, metadata);
    }

    public QUsuario(Class<? extends Usuario> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
    }
}
