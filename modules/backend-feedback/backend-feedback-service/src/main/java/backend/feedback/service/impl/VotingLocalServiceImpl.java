/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package backend.feedback.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.ws.rs.NotFoundException;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import backend.feedback.constants.VotingTerm;
import backend.feedback.exception.NoSuchVotingException;
import backend.feedback.model.Voting;
import backend.feedback.service.base.VotingLocalServiceBaseImpl;

/**
 * The implementation of the voting local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link backend.feedback.service.VotingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author sondt
 * @see VotingLocalServiceBaseImpl
 * @see backend.feedback.service.VotingLocalServiceUtil
 */
@ProviderType
public class VotingLocalServiceImpl extends VotingLocalServiceBaseImpl {
	
	@Indexable(type = IndexableType.REINDEX)
	public Voting addVoting(long userId, long groupId, String className, String classPK, String subject, String choices,
			String templateNo, boolean commentable, ServiceContext serviceContext) throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		long votingId = counterLocalService.increment(Voting.class.getName());

		Voting voting = votingPersistence.create(votingId);

		Date now = new Date();

		// Group instance
		voting.setGroupId(groupId);

		// Audit fields
		voting.setUuid(serviceContext.getUuid());
		voting.setCompanyId(user.getCompanyId());
		voting.setUserId(user.getUserId());
		voting.setUserName(user.getFullName());
		voting.setCreateDate(serviceContext.getCreateDate(now));
		voting.setModifiedDate(serviceContext.getCreateDate(now));

		voting.setClassName(className);
		voting.setClassPK(classPK);
		voting.setSubject(subject);
		voting.setChoices(choices);
		voting.setTemplateNo(templateNo);
		voting.setCommentable(commentable);

		voting.setExpandoBridgeAttributes(serviceContext);

		votingPersistence.update(voting);

		return voting;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws NotFoundException
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	public Voting deleteVote(long votingId, ServiceContext serviceContext) {

		try {

			return votingPersistence.remove(votingId);

		} catch (NoSuchVotingException e) {

			throw new NotFoundException();
		}

	}

	@Indexable(type = IndexableType.REINDEX)
	public Voting updateVote(long userId, long votingId, String className, String classPK, String subject,
			String choices, String templateNo, boolean commentable, ServiceContext serviceContext)
			throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		Voting voting = votingPersistence.fetchByPrimaryKey(votingId);

		if (Validator.isNull(voting)) {
			throw new NotFoundException();
		}

		Date now = new Date();

		// Audit fields
		voting.setUserId(user.getUserId());
		voting.setUserName(user.getFullName());
		voting.setModifiedDate(serviceContext.getCreateDate(now));

		voting.setClassName(className);
		voting.setClassPK(classPK);
		voting.setSubject(subject);
		voting.setChoices(choices);
		voting.setTemplateNo(templateNo);
		voting.setCommentable(commentable);

		voting.setExpandoBridgeAttributes(serviceContext);

		votingPersistence.update(voting);

		return voting;

	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Voting> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Voting.class);

		searchContext.addFullQueryEntryClassName(Voting.class.getName());
		searchContext.setEntryClassNames(new String[] { Voting.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(VotingTerm.CLASS_NAME);
		String classPK = (String) params.get(VotingTerm.CLASS_PK);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(VotingTerm.SUBJECT);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(VotingTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(VotingTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(VotingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(VotingTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Voting.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Voting> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Voting.class);

		searchContext.addFullQueryEntryClassName(Voting.class.getName());
		searchContext.setEntryClassNames(new String[] { Voting.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);
		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(VotingTerm.CLASS_NAME);
		String classPK = (String) params.get(VotingTerm.CLASS_PK);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(VotingTerm.SUBJECT);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(VotingTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(VotingTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(VotingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(VotingTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Voting.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}
}